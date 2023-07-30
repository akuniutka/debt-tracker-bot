package dev.akuniutka.debttracker.entity;

public enum OperationType {
    INCOME("I"),
    EXPENSE("E");

    private final String code;

    OperationType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
