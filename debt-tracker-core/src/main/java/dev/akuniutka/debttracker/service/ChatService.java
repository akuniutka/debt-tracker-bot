package dev.akuniutka.debttracker.service;

import dev.akuniutka.debttracker.entity.Chat;
import dev.akuniutka.debttracker.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public Chat getChat(Long id) {
        Chat chat = chatRepository.findChatById(id).orElse(new Chat());
        if (chat.getId() == null) {
            chat.setId(id);
            chatRepository.save(chat);
        }
        return chat;
    }
}
