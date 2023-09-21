package dev.akuniutka.debttracker.service;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.ui.telegram.ChatService;
import dev.akuniutka.debttracker.entity.ChatScript;
import dev.akuniutka.debttracker.entity.DebtTrackerChat;
import dev.akuniutka.debttracker.repository.DebtTrackerChatRepository;
import org.springframework.stereotype.Service;

@Service
public class DebtTrackerChatService implements ChatService {
    private final ChatScript chatScript;
    private final DebtTrackerChatRepository debtTrackerChatRepository;

    public DebtTrackerChatService(ChatScript chatScript, DebtTrackerChatRepository debtTrackerChatRepository) {
        if (chatScript == null) {
            throw new IllegalArgumentException("chat script is null");
        }
        this.chatScript = chatScript;
        this.debtTrackerChatRepository = debtTrackerChatRepository;
    }

    @Override
    public Chat getChat(Long userId) {
        DebtTrackerChat chat = debtTrackerChatRepository.findByUserId(userId).orElse(
                new DebtTrackerChat(userId, chatScript.getInitialChatState())
        );
        chat.setDao(debtTrackerChatRepository);
        return chat;
    }
}
