package dev.akuniutka.debttracker.script;

import java.util.Optional;
import java.util.stream.Stream;

public enum Command {
    START("/start"),
    BORROWED("/borrowed"),
    REPAID("/repaid"),
    LENT("/lent"),
    REDEEMED("/redeemed"),
    STATUS("/status"),
    HELP("/help"),
    CANCEL("/cancel"),
    SETTINGS("/settings");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static Optional<Command> getCommand(String string) {
        return Stream.of(Command.values())
                .filter(c -> c.getCommand().equals(string))
                .findAny();
    }
}
