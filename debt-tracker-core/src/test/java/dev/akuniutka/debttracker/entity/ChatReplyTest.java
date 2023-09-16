package dev.akuniutka.debttracker.entity;

import dev.akuniutka.chatbot.core.ChatReply;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatReplyTest {
    private static final List<String> REPLY = Arrays.asList("the first line", "the second line");
    private static final List<String> POSSIBLE_ANSWERS = Arrays.asList("agree", "reject");

    @Test
    void testChatReplyWhenReplyIsNull() {
        assertDoesNotThrow(() -> new ChatReply(null, new ArrayList<>(POSSIBLE_ANSWERS)));
    }

    @Test
    void testChatReplyWhenPossibleAnswersIsNull() {
        assertDoesNotThrow(() -> new ChatReply(new ArrayList<>(REPLY), null));
    }

    @Test
    void testChatReplyWhenBothReplyAndPossibleAnswersAreNull() {
        assertDoesNotThrow(() -> new ChatReply(null, null));
    }

    @Test
    void testChatReplyWhenBothReplyAndPossibleAnswersAreNotNull() {
        assertDoesNotThrow(() -> new ChatReply(new ArrayList<>(REPLY), new ArrayList<>(POSSIBLE_ANSWERS)));
    }

    @Test
    void testGetReplyWhenReplyIsNull() {
        ChatReply chatReply = new ChatReply(null, new ArrayList<>(POSSIBLE_ANSWERS));
        assertNull(chatReply.getReply());
    }

    @Test
    void testGetReplyWhenReplyIsNotNull() {
        ChatReply chatReply = new ChatReply(new ArrayList<>(REPLY), new ArrayList<>(POSSIBLE_ANSWERS));
        assertEquals(REPLY, chatReply.getReply());
    }

    @Test
    void testGetPossibleAnswersWhenPossibleAnswersIsNull() {
        ChatReply chatReply = new ChatReply(new ArrayList<>(REPLY), null);
        assertNull(chatReply.getPossibleAnswers());
    }

    @Test
    void testGetPossibleAnswersWhenPossibleAnswersIsNotnull() {
        ChatReply chatReply = new ChatReply(new ArrayList<>(REPLY), new ArrayList<>(POSSIBLE_ANSWERS));
        assertEquals(POSSIBLE_ANSWERS, chatReply.getPossibleAnswers());
    }
}