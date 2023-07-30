package dev.akuniutka.debttracker.entity;

public enum ChatStatus {
    WAITING_FOR_COMMAND("A"),
    WAITING_FOR_AMOUNT_OF_INCOME("B"),
    WAITING_FOR_AMOUNT_OF_EXPENSE("C");

    private final String code;
    ChatStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
