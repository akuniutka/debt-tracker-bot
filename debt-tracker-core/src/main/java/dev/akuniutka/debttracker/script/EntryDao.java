package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.Entry;

public interface EntryDao {
    Entry createDraft(Long userId);

    Entry getDraftByUserId(Long userId);

    Entry saveDraft(Entry entry);

    Entry save(Entry entry);
}
