package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.ChatState;

import java.util.stream.Stream;

public enum DebtTrackerChatState {
    WAITING_FOR_START(new WaitingForStartChatState()),
    WAITING_FOR_COMMAND(new WaitingForCommandChatState()),
    WAITING_FOR_CORRECT_COMMAND(new WaitingForCorrectCommandChatState()),
    SHOWING_CURRENT_STATUS(new ShowingCurrentStatusChatState()),
    WAITING_FOR_AMOUNT(new WaitingForAmountChatState()),
    WAITING_FOR_CORRECT_AMOUNT(new WaitingForCorrectAmountChatState()),
    WAITING_FOR_NAME(new WaitingForNameChatState()),
    WAITING_FOR_CORRECT_NAME(new WaitingForCorrectNameChatState());

    private final AbstractDebtTrackerChatState chatState;

    DebtTrackerChatState(AbstractDebtTrackerChatState chatState) {
        this.chatState = chatState;
    }

    public ChatState getChatState() {
        return chatState;
    }

    public static ChatState getChatState(int id) {
        return Stream.of(DebtTrackerChatState.values())
                .filter(e -> id == e.ordinal())
                .map(e -> e.chatState)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Chat state not found"));
    }

    public static int getChatStateId(ChatState chatState) {
        return Stream.of(DebtTrackerChatState.values())
                .filter(e -> chatState == e.chatState)
                .map(DebtTrackerChatState::ordinal)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Chat state not found"));
    }

    public static ChatState getInitialChatState() {
        return DebtTrackerChatState.WAITING_FOR_START.chatState;
    }

    public static void setEntryService(EntryService entryService) {
        Stream.of(DebtTrackerChatState.values())
                .forEach(a -> a.chatState.setEntryService(entryService));
    }
}
