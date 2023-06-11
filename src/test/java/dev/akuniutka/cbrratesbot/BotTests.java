package dev.akuniutka.cbrratesbot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

// TODO: implement 100% test coverage of the bot

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class BotTests {

    @Test
    void whenSpringContextLoadsShouldNoBeExceptions() {
    }

}
