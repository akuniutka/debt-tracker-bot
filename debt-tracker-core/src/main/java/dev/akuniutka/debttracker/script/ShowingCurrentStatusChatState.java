package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.debttracker.entity.Entry;
import dev.akuniutka.debttracker.entity.EntryType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class ShowingCurrentStatusChatState extends WaitingForCommandChatState {
    @Override
    public List<String> getReply(Chat chat) {
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
        String report = "you owe to:\n" + (oweTo.isEmpty() ? "none" : oweTo) +
                "\n\nyou are owed by:\n" + (owedBy.isEmpty() ? "none" : owedBy);
        List<String> reportReply = new ArrayList<>(reply);
        reportReply.add(0, report);
        return reportReply;
    }

    private BigDecimal signedAmount(Entry entry) {
        if (entry.getType() == EntryType.LENT || entry.getType() == EntryType.REPAID) {
            return entry.getAmount().negate();
        } else {
            return entry.getAmount();
        }
    }
}
