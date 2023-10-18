package io.github.akuniutka.debttrackerbot.entity;

public enum EntryType {
    BORROWED("B"),
    LENT("L"),
    REPAID("R"),
    REDEEMED("G");

    private final String code;

    EntryType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
