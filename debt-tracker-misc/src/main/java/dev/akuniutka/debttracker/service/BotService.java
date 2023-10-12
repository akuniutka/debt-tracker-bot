package dev.akuniutka.debttracker.service;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.debttracker.repository.ChatRepository;
import dev.akuniutka.debttracker.script.DebtTrackerChatState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
@Slf4j
public class BotService extends TelegramLongPollingBot {
    private final ChatRepository chatRepository;
    @Value("${bot.name}")
    private String botUsername;

    public BotService(@Value("${bot.api.key}") String botToken, ChatRepository chatRepository) {
        super(botToken);
        if (chatRepository == null) {
            throw new IllegalArgumentException("chat repository is null");
        }
        this.chatRepository = chatRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                Long userId = message.getChatId();
                String userMessage = message.getText();
                log.debug("message from Telegram user {}: {}", userId, userMessage);
                Chat chat = chatRepository.findByUserId(userId).orElse(
                        new Chat(userId, DebtTrackerChatState.getInitialChatState())
                );
                List<String> reply = chat.getReplyToMessage(userMessage);
                chatRepository.save(chat);
                if (reply != null && !reply.isEmpty()) {
                    for (String line : reply) {
                        SendMessage response = new SendMessage();
                        response.setChatId(String.valueOf(userId));
                        response.setText(line);
                        try {
                            execute(response);
                        } catch (TelegramApiException e) {
                            log.error("Error while sending message to Telegram", e);
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
