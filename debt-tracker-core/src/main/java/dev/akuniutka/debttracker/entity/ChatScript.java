package dev.akuniutka.debttracker.entity;

import dev.akuniutka.chatbot.ChatState;

public interface ChatScript {
    ChatState getInitialChatState();
    ChatState getChatState(long id);
//    int getChatStateId(ChatState chatState);
}
