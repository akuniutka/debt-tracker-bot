package dev.akuniutka.debttracker.service;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.ui.telegram.ChatService;
import dev.akuniutka.debttracker.entity.ChatScript;
import dev.akuniutka.debttracker.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebtTrackerChatService implements ChatService {
    private final ChatScript chatScript;
    private final ChatRepository chatRepository;

    public DebtTrackerChatService(ChatScript chatScript, ChatRepository chatRepository) {
        if (chatScript == null) {
            throw new IllegalArgumentException("chat script is null");
        }
        this.chatScript = chatScript;
        this.chatRepository = chatRepository;
    }

    @Override
    public List<String> getReplyForUser(Long userId, String userMessage) {
        Chat chat = getChat(userId);
        List<String> reply = chat.getReplyToMessage(userMessage);
        chatRepository.save(chat);
        return reply;
    }

    private Chat getChat(Long userId) {
        return chatRepository.findByUserId(userId).orElse(
                new Chat(userId, chatScript.getInitialChatState())
        );
    }
}
