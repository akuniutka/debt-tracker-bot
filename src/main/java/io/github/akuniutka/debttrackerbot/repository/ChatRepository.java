package io.github.akuniutka.debttrackerbot.repository;

import io.github.akuniutka.debttrackerbot.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByUserId(Long userId);
}