package dev.akuniutka.debttracker.service;

import dev.akuniutka.chatbot.ui.telegram.BotService;
import dev.akuniutka.chatbot.ui.telegram.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
public class DebtTrackerBotService extends BotService {

    public DebtTrackerBotService(@Value("${bot.name}") String botUsername, @Value("${bot.api.key}") String botToken, ChatService chatService) {
        super(botUsername, botToken, chatService);
    }

    @PostConstruct
    public void start() {
        log.info("Username: {}, token: [***]", getBotUsername());
    }
}
