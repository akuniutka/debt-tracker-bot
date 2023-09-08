package dev.akuniutka.debttracker.entity;

public enum ChatState {
    WAITING_FOR_COMMAND("A"),
    WAITING_FOR_AMOUNT_OF_INCOME("B"),
    WAITING_FOR_AMOUNT_OF_EXPENSE("C"),
    WAITING_FOR_CORRECT_COMMAND("D"),
    SHOWING_CURRENT_STATUS("E"),
    WAITING_FOR_AMOUNT("F"),
    WAITING_FOR_CORRECT_AMOUNT("G"),
    WAITING_FOR_ACCOUNT("H"),
    WAITING_FOR_CORRECT_AQCCOUNT("I");

    private final String code;

    ChatState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
