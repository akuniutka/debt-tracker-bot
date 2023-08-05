package dev.akuniutka.debttracker.entity;

public enum EntryType {
    BORROWED("B"),
    LENT("L"),
    REPAID("R"),
    GOT_BACK("G");

    private final String code;

    EntryType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
