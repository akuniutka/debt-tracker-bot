package dev.akuniutka.debttracker.entity;

import java.util.ArrayList;
import java.util.List;

public class WaitingForStartChatState implements ChatState {
    private static final String START_COMMAND = "/start";

    @Override
    public void processMessage(Chat chat, String message) {
        if (START_COMMAND.equals(message)) {
            chat.setChatState(new WaitingForCommandChatState());
        } else {
            chat.setChatState(new WaitingForCorrectCommandChatState());
        }
    }

    @Override
    public List<String> getReply() {
        return null;
    }

    @Override
    public List<String> getPossibleAnswers() {
        List<String> possibleAnswers = new ArrayList<>();
        possibleAnswers.add(START_COMMAND);
        return possibleAnswers;
    }
}
