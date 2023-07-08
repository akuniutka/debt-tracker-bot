package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.entity.ActiveChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActiveChatRepository extends JpaRepository<ActiveChat, Long> {
    Optional<ActiveChat> findActiveChatByChatId(Long chatId);
}
