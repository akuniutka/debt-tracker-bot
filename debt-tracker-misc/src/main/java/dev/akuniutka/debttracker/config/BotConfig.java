package dev.akuniutka.debttracker.config;

import dev.akuniutka.chatbot.ui.telegram.BotService;
import dev.akuniutka.debttracker.entity.ChatScript;
import dev.akuniutka.debttracker.script.DebtTrackerChatScript;
import dev.akuniutka.debttracker.service.DebtTrackerChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"dev.akuniutka.debttracker"})
@EnableJpaRepositories(basePackages = {"dev.akuniutka.debttracker"})
@ComponentScan(basePackages = {"dev.akuniutka.debttracker"})
@Slf4j
public class BotConfig {
    @Value("${bot.name}")
    private String botUsername;
    @Value("${bot.api.key}")
    private String botToken;
    // TODO: limit Swagger to custom endpoints only (with Docket bean)

    @Bean
    public ChatScript getChatScript() {
        return new DebtTrackerChatScript();
    }

    @Bean
    @Autowired
    public BotService getBotService(DebtTrackerChatService chatService) {
        return new BotService(botUsername, botToken, chatService);
    }
}
