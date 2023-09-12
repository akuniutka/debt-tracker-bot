package dev.akuniutka.debttracker.script;

enum DebtTrackerChatState {
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

    public AbstractDebtTrackerChatState getChatState() {
        return chatState;
    }
}
