package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.EntryDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryDraftRepository extends JpaRepository<EntryDraft, Long> {
    Optional<EntryDraft> findByUserId(Long userId);
}
