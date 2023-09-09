package dev.akuniutka.debttracker.entity;

import java.util.List;

public class WaitingForCorrectCommandChatState extends WaitingForCommandChatState {
    private static final String WRONG_COMMAND_MESSAGE = "The command is unknown.";

    @Override
    public List<String> getReply() {
        List<String> reply = super.getReply();
        reply.add(0, WRONG_COMMAND_MESSAGE);
        return reply;
    }
}
