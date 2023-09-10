package dev.akuniutka.debttracker.entity;

public interface ChatScript {
    ChatState getInitialChatState();
    ChatState getChatState(int id);
    int getChatStateId(ChatState chatState);
}
