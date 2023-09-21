package dev.akuniutka.chatbot.ui.telegram;

import dev.akuniutka.chatbot.core.Chat;

public interface ChatService {
    Chat getChat(Long userId);
}
