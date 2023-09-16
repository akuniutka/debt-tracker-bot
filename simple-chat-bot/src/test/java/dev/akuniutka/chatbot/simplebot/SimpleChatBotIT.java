package dev.akuniutka.chatbot.simplebot;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleChatBotIT {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String PROMPT = "Send message to bot or 'exit' to quit:" + SEPARATOR;
    private static final String REPLY_TO_HELLO = "Bot:" + SEPARATOR +
            "\t" + "Hello!" + SEPARATOR +
            "\t" + "Nice to see you!" + SEPARATOR;
    private static final String REPLY_TO_HI = "Bot:" + SEPARATOR +
            "\t" + "Hi!" + SEPARATOR +
            "\t" + "Nice to see you!" + SEPARATOR;
    private static final String REPLY_TO_UNKNOWN = "Bot:" + SEPARATOR +
            "\t" + "Sorry! I have not understood it." + SEPARATOR +
            "\t" + "Could you rephrase it?" + SEPARATOR;
    private static final String ANSWERS_FOR_HELLO = "Possible answers:" + SEPARATOR +
            "\t" + "Hi" + SEPARATOR +
            "\t" + "Hello" + SEPARATOR;
    private static final String ANSWERS_FOR_HI = "Possible answers:" + SEPARATOR +
            "\t" + "Hello" + SEPARATOR +
            "\t" + "Hi" + SEPARATOR;
    private static final InputStream SYS_IN_BACKUP = System.in;
    private static final PrintStream SYS_OUT_BACKUP = System.out;
    private static final PrintStream SYS_ERR_BACKUP = System.err;
    private ByteArrayOutputStream out;
    private ByteArrayOutputStream err;

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        err = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    void tearDown() {
        System.setIn(SYS_IN_BACKUP);
        System.setOut(SYS_OUT_BACKUP);
        System.setErr(SYS_ERR_BACKUP);
    }

    @Test
    void testMainWhenJustExit() {
        String input = "exit" + SEPARATOR;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String[] args = new String[1];
        SimpleChatBot.main(args);
        assertEquals(PROMPT, out.toString());
        assertEquals(0, err.size());
    }

    @Test
    void testMainWhenHelloAndExit() {
        String expected = PROMPT + REPLY_TO_HELLO + ANSWERS_FOR_HELLO + PROMPT;
        String input = "Hello" + SEPARATOR + "exit" + SEPARATOR;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String[] args = new String[1];
        SimpleChatBot.main(args);
        assertEquals(expected, out.toString());
        assertEquals(0, err.size());
    }

    @Test
    void testMainWhenHiAndExit() {
        String expected = PROMPT + REPLY_TO_HI + ANSWERS_FOR_HI + PROMPT;
        String input = "Hi" + SEPARATOR + "exit" + SEPARATOR;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String[] args = new String[1];
        SimpleChatBot.main(args);
        assertEquals(expected, out.toString());
        assertEquals(0, err.size());
    }

    @Test
    void testMainWhenNeitherHelloNorHiAndExit() {
        String expected = PROMPT + REPLY_TO_UNKNOWN + ANSWERS_FOR_HELLO + PROMPT;
        String input = "OK" + SEPARATOR + "exit" + SEPARATOR;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String[] args = new String[1];
        SimpleChatBot.main(args);
        assertEquals(expected, out.toString());
        assertEquals(0, err.size());
    }

    @Test
    void testMainWhenSeveralCommandsAndExit() {
        String expected = PROMPT + REPLY_TO_HELLO + ANSWERS_FOR_HELLO + PROMPT +
                REPLY_TO_HI + ANSWERS_FOR_HI + PROMPT +
                REPLY_TO_UNKNOWN + ANSWERS_FOR_HELLO + PROMPT;
        String input = "Hello" + SEPARATOR + "Hi" + SEPARATOR + "OK" + SEPARATOR + "exit" + SEPARATOR;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String[] args = new String[1];
        SimpleChatBot.main(args);
        assertEquals(expected, out.toString());
        assertEquals(0, err.size());
    }
}