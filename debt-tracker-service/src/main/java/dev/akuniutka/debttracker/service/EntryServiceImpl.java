package dev.akuniutka.debttracker.service;

import dev.akuniutka.debttracker.entity.Entry;
import dev.akuniutka.debttracker.entity.EntryDraft;
import dev.akuniutka.debttracker.entity.EntryType;
import dev.akuniutka.debttracker.repository.EntryDraftRepository;
import dev.akuniutka.debttracker.repository.EntryRepository;
import dev.akuniutka.debttracker.script.DebtTrackerChatState;
import dev.akuniutka.debttracker.script.EntryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {
    private final EntryDraftRepository entryDraftRepository;
    private final EntryRepository entryRepository;

    public EntryServiceImpl(EntryDraftRepository entryDraftRepository, EntryRepository entryRepository) {
        if (entryDraftRepository == null && entryRepository == null) {
            throw new IllegalArgumentException("entryDraftRepository and entryRepository cannot be null");
        } else if (entryDraftRepository == null) {
            throw new IllegalArgumentException("entryDraftRepository cannot be null");
        } else if (entryRepository == null) {
            throw new IllegalArgumentException("entryRepository cannot be null");
        }
        this.entryDraftRepository = entryDraftRepository;
        this.entryRepository = entryRepository;
        DebtTrackerChatState.setEntryService(this);
    }

    @Override
    public void updateDraft(Long userId, EntryType type) {
        EntryDraft entryDraft = entryDraftRepository.findByUserId(userId).orElse(new EntryDraft(userId));
        entryDraft.setType(type);
        entryDraftRepository.save(entryDraft);
    }

    @Override
    public void updateDraft(Long userId, BigDecimal amount) {
        EntryDraft entryDraft = entryDraftRepository.findByUserId(userId).orElse(new EntryDraft(userId));
        entryDraft.setAmount(amount);
        entryDraftRepository.save(entryDraft);
    }

    @Override
    public void updateDraft(Long userId, String account) {
        EntryDraft entryDraft = entryDraftRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException("Entry draft not found")
        );
        Entry entry = new Entry(userId, entryDraft.getType(), entryDraft.getAmount(), account, OffsetDateTime.now());
        entryRepository.save(entry);
        entryDraftRepository.delete(entryDraft);
    }

    @Override
    public void dropDraft(Long userId) {
        EntryDraft entryDraft = entryDraftRepository.findByUserId(userId).orElseThrow(
                () -> new RuntimeException("Entry draft not found")
        );
        entryDraftRepository.delete(entryDraft);
    }

    @Override
    public List<Entry> getAllEntries(Long userId) {
        return entryRepository.findAllByUserId(userId);
    }
}
