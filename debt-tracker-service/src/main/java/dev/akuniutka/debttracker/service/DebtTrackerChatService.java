package dev.akuniutka.debttracker.service;

import dev.akuniutka.chatbot.ui.telegram.ChatService;
import dev.akuniutka.debttracker.entity.ChatScript;
import dev.akuniutka.debttracker.entity.DebtTrackerChat;
import dev.akuniutka.debttracker.repository.DebtTrackerChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<String> getReplyForUser(Long userId, String userMessage) {
        DebtTrackerChat chat = getChat(userId);
        List<String> reply = chat.getReplyToMessage(userMessage);
        debtTrackerChatRepository.save(chat);
        return reply;
    }

    private DebtTrackerChat getChat(Long userId) {
        return debtTrackerChatRepository.findByUserId(userId).orElse(
                new DebtTrackerChat(userId, chatScript.getInitialChatState())
        );
    }
}
