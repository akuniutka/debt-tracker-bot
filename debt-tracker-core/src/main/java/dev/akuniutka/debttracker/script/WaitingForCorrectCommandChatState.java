package dev.akuniutka.debttracker.script;

import java.util.List;

class WaitingForCorrectCommandChatState extends WaitingForCommandChatState {
    private static final String WRONG_COMMAND_MESSAGE = "The command is unknown.";

    WaitingForCorrectCommandChatState(DebtTrackerChatScript chatScript) {
        super(chatScript);
    }

    @Override
    public List<String> getReply() {
        List<String> reply = super.getReply();
        reply.add(0, WRONG_COMMAND_MESSAGE);
        return reply;
    }
}
