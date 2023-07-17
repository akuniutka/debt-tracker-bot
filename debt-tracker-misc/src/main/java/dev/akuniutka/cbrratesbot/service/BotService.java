package dev.akuniutka.cbrratesbot.service;


import dev.akuniutka.cbrratesbot.entity.ActiveChat;
import dev.akuniutka.cbrratesbot.repository.ActiveChatRepository;
import dev.akuniutka.debttracker.entity.Chat;
import dev.akuniutka.debttracker.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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
    private final ChatService chatService;
    @Value("${bot.api.key}")
    private String apiKey;
    @Value("${bot.name}")
    private String name;
    private final Map<Long, List<String>> commandsHistory = new ConcurrentHashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        // TODO: to add retrieving telegram user_id from chat_id
        Chat chat = chatService.getChat(chatId);
        log.debug("Message from chat: {}", chat);
        String command = message.getText();
        log.debug("New command from Telegram chat {}: {}", chatId, command);
        try {
            if (activeChatRepository.findActiveChatByChatId(chatId).isEmpty()) {
                ActiveChat activeChat = new ActiveChat();
                activeChat.setChatId(chatId);
                activeChatRepository.save(activeChat);
                log.info("Added chat {} to the list of active chats", chatId);
            }
            String answer;
            if (CURRENT_RATES.equalsIgnoreCase(command)) {
                answer = cbrService.getExchangeRates().stream()
                        .map(exchangeRate -> exchangeRate.getCurrency() + " - " + exchangeRate.getValue())
                        .collect(Collectors.joining("\n"));
            } else if (ADD_INCOME.equalsIgnoreCase(command)) {
                answer = "Отправьте мне сумму полученного дохода";
            } else if (ADD_EXPENSE.equalsIgnoreCase(command)) {
                answer = "Отправьте мне сумму расходов";
            } else if (ADD_INCOME.equalsIgnoreCase(getPreviousCommand(chatId))
                    || ADD_EXPENSE.equalsIgnoreCase(getPreviousCommand(chatId))) {
                answer = financeService.addFinanceOperation(getPreviousCommand(chatId), message.getText(), chatId);
            } else {
                answer = "Неверная команда";
            }
            addCommandToHistory(chatId, command);

            SendMessage response = new SendMessage();
            response.setChatId(String.valueOf(chatId));
            response.setText(answer);
            execute(response);
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
        log.info("Username: {}, token: [***]", name);
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
        commandsHistory.computeIfAbsent(chatId, key -> new ArrayList<>()).add(command);
    }

    private String getPreviousCommand(Long chatId) {
        List<String> chatHistory = commandsHistory.computeIfAbsent(chatId, key -> new ArrayList<>());
        return chatHistory.isEmpty() ? null : chatHistory.get(chatHistory.size() - 1);
    }
}
