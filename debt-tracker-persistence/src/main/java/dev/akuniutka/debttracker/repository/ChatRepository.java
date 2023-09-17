package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.DebtTrackerChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<DebtTrackerChat, Long> {
    Optional<DebtTrackerChat> findByUserId(Long userId);
}