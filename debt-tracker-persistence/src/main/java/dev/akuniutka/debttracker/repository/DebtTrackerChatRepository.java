package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.DebtTrackerChat;
import dev.akuniutka.debttracker.entity.DebtTrackerChatDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebtTrackerChatRepository extends JpaRepository<DebtTrackerChat, Long>, DebtTrackerChatDao {
    Optional<DebtTrackerChat> findByUserId(Long userId);
}