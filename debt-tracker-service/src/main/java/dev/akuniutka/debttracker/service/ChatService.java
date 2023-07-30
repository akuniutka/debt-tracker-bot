package dev.akuniutka.debttracker.service;

import dev.akuniutka.debttracker.entity.Chat;
import dev.akuniutka.debttracker.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public Chat getChatOrCreateNew(Long id) {
        return Chat.getChatOrCreateNew(id, chatRepository);
    }

    public Optional<Chat> getChat(Long id) {
        return chatRepository.findById(id);
    }
}
