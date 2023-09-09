package dev.akuniutka.debttracker.entity;

public enum ChatState {
    WAITING_FOR_START("A"),
    WAITING_FOR_COMMAND("B"),
    WAITING_FOR_CORRECT_COMMAND("C"),
    SHOWING_CURRENT_STATUS("D"),
    WAITING_FOR_AMOUNT("E"),
    WAITING_FOR_CORRECT_AMOUNT("F"),
    WAITING_FOR_ACCOUNT("G"),
    WAITING_FOR_CORRECT_ACCOUNT("H");

    private final String code;

    ChatState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
