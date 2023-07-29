package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.dao.Dao;
import dev.akuniutka.debttracker.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>, Dao<Chat> {
    Optional<Chat> findChatById(Long id);
}
