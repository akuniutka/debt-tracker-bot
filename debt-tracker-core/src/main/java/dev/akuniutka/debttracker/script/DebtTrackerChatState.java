package dev.akuniutka.debttracker.script;

enum DebtTrackerChatState {
    WAITING_FOR_START(0),
    WAITING_FOR_COMMAND(1),
    WAITING_FOR_CORRECT_COMMAND(2),
    SHOWING_CURRENT_STATUS(3),
    WAITING_FOR_AMOUNT(4),
    WAITING_FOR_CORRECT_AMOUNT(5),
    WAITING_FOR_NAME(6),
    WAITING_FOR_CORRECT_NAME(7);

    private final int id;

    DebtTrackerChatState(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
