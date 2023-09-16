package dev.akuniutka.debttracker.entity;

import dev.akuniutka.chatbot.core.ChatState;

public interface ChatScript {
    ChatState getInitialChatState();
    ChatState getChatState(int id);
    int getChatStateId(ChatState chatState);
}
