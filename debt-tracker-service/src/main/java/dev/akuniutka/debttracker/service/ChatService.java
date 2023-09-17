package dev.akuniutka.debttracker.service;

import dev.akuniutka.chatbot.core.ChatReply;
import dev.akuniutka.debttracker.entity.ChatScript;
import dev.akuniutka.debttracker.entity.DebtTrackerChat;
import dev.akuniutka.debttracker.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {
    private final ChatScript chatScript;
    private final ChatRepository chatRepository;

    public ChatService(ChatScript chatScript, ChatRepository chatRepository) {
        if (chatScript == null) {
            throw new IllegalArgumentException("chat script is null");
        }
        this.chatScript = chatScript;
        this.chatRepository = chatRepository;
    }

    public List<String> getReplyForChat(Long chatId, String message) {
        DebtTrackerChat chat = chatRepository.findByUserId(chatId).orElse(
                new DebtTrackerChat(chatId, chatScript.getInitialChatState())
        );
        ChatReply chatReply = chat.getReplyToMessage(message);
        chatRepository.save(chat);
        return chatReply.getReply();
    }

    public Optional<DebtTrackerChat> getChat(Long id) {
        return chatRepository.findById(id);
    }
}
