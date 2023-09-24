package dev.akuniutka.chatbot.ui.telegram;

import java.util.List;

public interface ChatService {
    List<String> getReplyForUser(Long userId, String userMessage);
}
