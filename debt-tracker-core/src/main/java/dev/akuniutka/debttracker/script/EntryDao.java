package dev.akuniutka.debttracker.script;

public interface EntryDao {
    Entry createDraft(Long userId);
    Entry getDraftByUserId(Long userId);
    Entry saveDraft(Entry entry);
    Entry save(Entry entry);
}
