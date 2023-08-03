package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.ActiveChat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ActiveChatRepositoryTest {

    @Autowired
    private ActiveChatRepository activeChatRepository;

    @Test
    void testFindActiveChatByCharIdIfExists() {
        Optional<ActiveChat> activeChatByChatId = activeChatRepository.findActiveChatByChatId(888888888L);
        assertTrue(activeChatByChatId.isPresent());
        assertEquals(888888888L, activeChatByChatId.get().getChatId());
    }

    @Test
    void testFindActiveChatByChatIdIfDoesNotExist() {
        Optional<ActiveChat> activeChatByChatId = activeChatRepository.findActiveChatByChatId(999999999L);
        assertFalse(activeChatByChatId.isPresent());
    }

}