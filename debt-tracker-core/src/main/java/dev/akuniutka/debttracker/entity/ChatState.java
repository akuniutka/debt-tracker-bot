package dev.akuniutka.debttracker.entity;

public enum ChatState {
    WAITING_FOR_COMMAND("A"),
    WAITING_FOR_CORRECT_COMMAND("B"),
    SHOWING_CURRENT_STATUS("C"),
    WAITING_FOR_AMOUNT("D"),
    WAITING_FOR_CORRECT_AMOUNT("E"),
    WAITING_FOR_ACCOUNT("F"),
    WAITING_FOR_CORRECT_ACCOUNT("G");

    private final String code;

    ChatState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
