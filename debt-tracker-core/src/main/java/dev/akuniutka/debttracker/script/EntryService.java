package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.Entry;
import dev.akuniutka.debttracker.entity.EntryType;

import java.math.BigDecimal;
import java.util.List;

public interface EntryService {
    void updateDraft(Long userId, EntryType type);

    void updateDraft(Long userId, BigDecimal amount);

    void updateDraft(Long userId, String account);

    List<Entry> getAllEntries(Long userId);
}
