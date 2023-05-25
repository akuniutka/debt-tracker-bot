package dev.akuniutka.cbrratesbot.service;

import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.entity.ActiveChat;
import dev.akuniutka.cbrratesbot.repository.ActiveChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// TODO: refactor the class

@Component
@RequiredArgsConstructor
@Slf4j
public class ScheduleService {
    private final ActiveChatRepository activeChatRepository;
    private final BotService botService;
    private final CbrService cbrService;
    private final List<ExchangeRate> previousRates = new ArrayList<>();

    @Scheduled(cron = "0 0 0/3 ? * *")
    public void notifyAboutChangesInExchangeRate() {
        try {
            List<ExchangeRate> currentRates = cbrService.getExchangeRates();
            Set<Long> activeChatIds =
                    activeChatRepository.findAll().stream().map(ActiveChat::getChatId).collect(Collectors.toSet());
            if (!previousRates.isEmpty()) {
                for (int index = 0; index < currentRates.size(); index++) {
                    if (currentRates.get(index).getValue() - previousRates.get(index).getValue() >= 10.0) {
                        botService.sendNotificationToAllActiveChats("Курс " +
                                currentRates.get(index).getCurrency() + " увеличился на 10+ рублей", activeChatIds
                        );
                    } else if (previousRates.get(index).getValue() - currentRates.get(index).getValue() >= 10.0) {
                        botService.sendNotificationToAllActiveChats("Курс " +
                                currentRates.get(index).getCurrency() + " уменьшился на 10+ рублей", activeChatIds
                        );
                    }
                }
                previousRates.clear();
            }
            previousRates.addAll(currentRates);
        } catch (DatatypeConfigurationException | IllegalStateException e) {
            log.error("Error while reading data from the Bank of Russia", e);
        }
    }
}
