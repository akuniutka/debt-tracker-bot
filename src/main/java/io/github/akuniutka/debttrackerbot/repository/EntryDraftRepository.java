package io.github.akuniutka.debttrackerbot.repository;

import io.github.akuniutka.debttrackerbot.entity.EntryDraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntryDraftRepository extends JpaRepository<EntryDraft, Long> {
    Optional<EntryDraft> findByUserId(Long userId);
}
