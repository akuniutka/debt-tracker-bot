package dev.akuniutka.cbrratesbot.service;


import dev.akuniutka.cbrratesbot.dto.ExchangeRate;
import dev.akuniutka.cbrratesbot.entity.ActiveChat;
import dev.akuniutka.cbrratesbot.repository.ActiveChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class BotService extends TelegramLongPollingBot {
    private static final String CURRENT_RATES = "/currentrates";
    private static final String ADD_INCOME = "/addincome";
    private static final String ADD_EXPENSE = "/addexpense";
    private final CbrService cbrService;
    private final FinanceService financeService;
    private final ActiveChatRepository activeChatRepository;
    @Value("${bot.api.key}")
    private String apiKey;
    @Value("${bot.name}")
    private String name;
    private final Map<Long, List<String>> commandsHistory = new ConcurrentHashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        log.debug("New command from telegram chat {}: {}", message.getChatId(), message.getText());
        try {
            SendMessage response = new SendMessage();
            Long chatId = message.getChatId();
            response.setChatId(String.valueOf(chatId));
            if (CURRENT_RATES.equalsIgnoreCase(message.getText())) {
                for (ExchangeRate exchangeRate : cbrService.getExchangeRates()) {
                    response.setText(StringUtils.defaultIfBlank(response.getText(), "") +
                            exchangeRate.getCurrency() + " - " + exchangeRate.getValue() + "\n"
                    );
                }
            } else if (ADD_INCOME.equalsIgnoreCase((message.getText()))) {
                response.setText("Отправьте мне сумму полученного дохода");
            } else if (ADD_EXPENSE.equalsIgnoreCase(message.getText())) {
                response.setText("Отправьте мне сумму расходов");
            } else {
                response.setText(
                        financeService.addFinanceOperation(
                                getPreviousCommand(message.getChatId()),
                                message.getText(),
                                message.getChatId()
                        )
                );
            }
            addCommandToHistory(message.getChatId(), message.getText());
            execute(response);
            if (activeChatRepository.findActiveChatByChatId(chatId).isEmpty()) {
                ActiveChat activeChat = new ActiveChat();
                activeChat.setChatId(chatId);
                activeChatRepository.save(activeChat);
                log.info("Added chat {} to the list of active chats", chatId);
            }
        } catch (TelegramApiException e) {
            log.error("Error while sending message to Telegram", e);
        } catch (DatatypeConfigurationException | IllegalStateException e) {
            log.error("Error while reading data from the Bank of Russia", e);
        }
    }

    public void sendNotificationToAllActiveChats(String message, Set<Long> chatIds) {
        for (Long id : chatIds) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(id));
            sendMessage.setText(message);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Error while sending message to Telegram", e);
            }
        }
    }

    @PostConstruct
    public void start() {
        log.info("Username: {}, token: {}", name, apiKey);
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return apiKey;
    }

    private void addCommandToHistory(Long chatId, String command) {
        if (commandsHistory.get(chatId) == null) {
            List<String> commands = new ArrayList<>();
            commands.add(command);
            commandsHistory.put(chatId, commands);
        } else {
            commandsHistory.get(chatId).add(command);
        }
    }

    private String getPreviousCommand(Long chatId) {
        return commandsHistory.get(chatId).get(commandsHistory.get(chatId).size() - 1);
    }
}
