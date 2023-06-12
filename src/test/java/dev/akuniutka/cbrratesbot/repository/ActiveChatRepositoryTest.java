package dev.akuniutka.cbrratesbot.repository;

import dev.akuniutka.cbrratesbot.entity.ActiveChat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
// TODO: remove after migrating to Hibernate 6 (in Spring Boot 3?) which introduced automatic dialect resolution
@TestPropertySource(properties = {"spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect"})
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
        ActiveChat activeChat = new ActiveChat();
        activeChat.setChatId(12345L);
        activeChatRepository.save(activeChat);
        Optional<ActiveChat> activeChatByChatId = activeChatRepository.findActiveChatByChatId(999999999L);
        assertFalse(activeChatByChatId.isPresent());
    }

}