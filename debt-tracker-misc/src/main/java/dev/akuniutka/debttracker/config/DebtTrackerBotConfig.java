package dev.akuniutka.debttracker.config;

import dev.akuniutka.chatbot.ui.telegram.BotService;
import dev.akuniutka.debttracker.service.DebtTrackerChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebtTrackerBotConfig {
    @Value("${bot.name}")
    private String botUsername;
    @Value("${bot.api.key}")
    private String botToken;

    @Bean
    @Autowired
    public BotService getBotService(DebtTrackerChatService chatService) {
        return new BotService(botUsername, botToken, chatService);
    }
}
