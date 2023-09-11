package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.ChatState;

enum DebtTrackerChatState {
    WAITING_FOR_START(new WaitingForStartChatState()),
    WAITING_FOR_COMMAND(new WaitingForCommandChatState()),
    WAITING_FOR_CORRECT_COMMAND(new WaitingForCorrectCommandChatState()),
    SHOWING_CURRENT_STATUS(null),
    WAITING_FOR_AMOUNT(null),
    WAITING_FOR_CORRECT_AMOUNT(null),
    WAITING_FOR_NAME(null),
    WAITING_FOR_CORRECT_NAME(null);

    private final ChatState chatState;

    DebtTrackerChatState(ChatState chatState) {
        this.chatState = chatState;
    }

    public ChatState getChatState() {
        return chatState;
    }
}
