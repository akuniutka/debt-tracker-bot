package io.github.akuniutka.debttrackerbot.service;

import io.github.akuniutka.debttrackerbot.entity.Chat;
import io.github.akuniutka.debttrackerbot.repository.ChatRepository;
import io.github.akuniutka.debttrackerbot.script.ChatState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
                        new Chat(userId, ChatState.WAITING_FOR_COMMAND)
                );
                chat.setBotService(this);
                chat.addUserMessage(userMessage);
                chatRepository.save(chat);
            }
        }
    }

    public void sendMessage(Long userId, String message) {
        try {
            execute(new SendMessage(String.valueOf(userId), message));
        } catch (TelegramApiException e) {
            log.error("Error while sending message to Telegram", e);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
