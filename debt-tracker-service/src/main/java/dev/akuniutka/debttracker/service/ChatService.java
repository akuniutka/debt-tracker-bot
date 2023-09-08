package dev.akuniutka.debttracker.service;

import dev.akuniutka.debttracker.entity.Chat;
import dev.akuniutka.debttracker.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public List<String> getReplyForChat(Long chatId, String message) {
        Chat chat = chatRepository.findById(chatId).orElse(new Chat(chatId));
        List<String> reply = chat.getReply(message);
        chatRepository.save(chat);
        return reply;
    }

    public Optional<Chat> getChat(Long id) {
        return chatRepository.findById(id);
    }
}
