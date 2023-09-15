package dev.akuniutka.debttracker.entity;

public interface ChatScript {
    ChatState getInitialChatState();
    ChatState getChatState(long id);
//    int getChatStateId(ChatState chatState);
}
