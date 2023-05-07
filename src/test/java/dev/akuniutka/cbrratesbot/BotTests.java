package dev.akuniutka.cbrratesbot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bot.class)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class BotTests {

    @Test
    void whenSpringContextLoadsShouldNoBeExceptions() {
    }

}
