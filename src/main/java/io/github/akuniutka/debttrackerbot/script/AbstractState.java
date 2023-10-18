package io.github.akuniutka.debttrackerbot.script;

import io.github.akuniutka.debttrackerbot.entity.Chat;
import io.github.akuniutka.debttrackerbot.entity.Entry;
import io.github.akuniutka.debttrackerbot.entity.EntryType;
import io.github.akuniutka.debttrackerbot.service.EntryService;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

abstract class AbstractState {
    protected static final String ENTER_COMMAND = "please enter a command";
    protected static final String ENTER_AMOUNT = "please enter an amount";
    protected static final String ENTER_ACCOUNT = "please enter account's name";
    protected static final String UNKNOWN_COMMAND = "an unknown command";
    protected static final String WRONG_AMOUNT = "an invalid amount";
    protected EntryService entryService;

    public void setEntryService(EntryService entryService) {
        this.entryService = entryService;
    }

    public abstract void addUserMessage(Chat chat, String message);

    protected EntryType entryTypeFromCommand(Command command) {
        switch (command) {
            case BORROWED:
                return EntryType.BORROWED;
            case REPAID:
                return EntryType.REPAID;
            case LENT:
                return EntryType.LENT;
            case REDEEMED:
                return EntryType.REDEEMED;
        }
        throw new IllegalArgumentException("No entry type for the command " + command);
    }

    protected String getCurrentDebtsReport(Chat chat) {
        Map<String, BigDecimal> accounts = entryService.getAllEntries(chat.getUserId()).stream()
                .collect(Collectors.toMap(Entry::getAccount, this::signedAmount, BigDecimal::add));
        String oweTo = accounts.entrySet().stream()
                .filter(e -> e.getValue().signum() > 0)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(e -> e.getKey() + ": " + e.getValue())
                .collect(Collectors.joining("\n"));
        String owedBy = accounts.entrySet().stream()
                .filter(e -> e.getValue().signum() < 0)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(e -> e.getKey() + ": " + e.getValue().negate())
                .collect(Collectors.joining("\n"));
        return "you owe to:\n" + (oweTo.isEmpty() ? "none" : oweTo) +
                "\n\nyou are owed by:\n" + (owedBy.isEmpty() ? "none" : owedBy);
    }

    private BigDecimal signedAmount(Entry entry) {
        if (entry.getType() == EntryType.LENT || entry.getType() == EntryType.REPAID) {
            return entry.getAmount().negate();
        } else {
            return entry.getAmount();
        }
    }
}
