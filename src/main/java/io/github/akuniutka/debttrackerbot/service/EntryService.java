package io.github.akuniutka.debttrackerbot.service;

import io.github.akuniutka.debttrackerbot.entity.Entry;
import io.github.akuniutka.debttrackerbot.entity.EntryDraft;
import io.github.akuniutka.debttrackerbot.entity.EntryType;
import io.github.akuniutka.debttrackerbot.repository.EntryDraftRepository;
import io.github.akuniutka.debttrackerbot.repository.EntryRepository;
import io.github.akuniutka.debttrackerbot.script.ChatState;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class EntryService {
    private final EntryDraftRepository entryDraftRepository;
    private final EntryRepository entryRepository;

    public EntryService(EntryDraftRepository entryDraftRepository, EntryRepository entryRepository) {
        if (entryDraftRepository == null && entryRepository == null) {
            throw new IllegalArgumentException("entryDraftRepository and entryRepository cannot be null");
        } else if (entryDraftRepository == null) {
            throw new IllegalArgumentException("entryDraftRepository cannot be null");
        } else if (entryRepository == null) {
            throw new IllegalArgumentException("entryRepository cannot be null");
        }
        this.entryDraftRepository = entryDraftRepository;
        this.entryRepository = entryRepository;
        ChatState.setEntryService(this);
    }

    public void updateDraft(Long userId, EntryType type) {
        EntryDraft entryDraft = entryDraftRepository.findByUserId(userId).orElse(new EntryDraft(userId));
        entryDraft.setType(type);
        entryDraftRepository.save(entryDraft);
    }

    public void updateDraft(Long userId, BigDecimal amount) {
        EntryDraft entryDraft = entryDraftRepository.findByUserId(userId).orElse(new EntryDraft(userId));
        entryDraft.setAmount(amount);
        entryDraftRepository.save(entryDraft);
    }

    public void updateDraft(Long userId, String account) {
        EntryDraft entryDraft = entryDraftRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException("Entry draft not found")
        );
        Entry entry = new Entry(userId, entryDraft.getType(), entryDraft.getAmount(), account, OffsetDateTime.now());
        entryRepository.save(entry);
        entryDraftRepository.delete(entryDraft);
    }

    public void dropDraft(Long userId) {
        EntryDraft entryDraft = entryDraftRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException("Entry draft not found")
        );
        entryDraftRepository.delete(entryDraft);
    }

    public List<Entry> getAllEntries(Long userId) {
        return entryRepository.findAllByUserId(userId);
    }
}
