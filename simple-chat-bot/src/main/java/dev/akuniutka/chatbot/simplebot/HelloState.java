package dev.akuniutka.chatbot.simplebot;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.core.ChatState;

import java.util.Arrays;
import java.util.List;

public class HelloState implements ChatState {
    @Override
    public void processMessage(Chat chat, String message) {
        if ("Hello".equals(message)) {
            chat.setState(new HelloState());
        } else if ("Hi".equals(message)) {
            chat.setState(new HiState());
        } else {
            chat.setState(new AskToRephraseState());
        }
    }

    @Override
    public List<String> getReply() {
        return Arrays.asList("Hello!", "Nice to see you!");
    }
}
