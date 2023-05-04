package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.dto.ValuteCursOnDate;
import dev.akuniutka.cbrratesbot.entity.ActiveChat;
import dev.akuniutka.cbrratesbot.repository.ActiveChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleService {
    private final ActiveChatRepository activeChatRepository;
    private final BotService botService;
    private final CbrService cbrService;
    private final List<ValuteCursOnDate> previousRates = new ArrayList<>();

    @Scheduled(cron = "0 0 0/3 ? * *")
    public void notifyAboutChangesInCurrencyRate() {
        try {
            List<ValuteCursOnDate> currentRates = cbrService.getCurrenciesFromCbr();
            Set<Long> activeChatIds =
                    activeChatRepository.findAll().stream().map(ActiveChat::getChatId).collect(Collectors.toSet());
            if (!previousRates.isEmpty()) {
                for (int index = 0; index < currentRates.size(); index++) {
                    if (currentRates.get(index).getCourse() - previousRates.get(index).getCourse() >= 10.0) {
                        botService.sendNotificationToAllActiveChats("Курс " +
                                currentRates.get(index).getName() + " увеличился на 10 рублей", activeChatIds
                        );
                    } else if (previousRates.get(index).getCourse() - currentRates.get(index).getCourse() >= 10.0) {
                        botService.sendNotificationToAllActiveChats("Курс " +
                                currentRates.get(index).getName() + " уменьшися на 10 рублей", activeChatIds
                        );
                    }
                }
            } else {
                previousRates.addAll(currentRates);
            }
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
    }
}
