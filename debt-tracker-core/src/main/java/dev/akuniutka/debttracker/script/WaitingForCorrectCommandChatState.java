package dev.akuniutka.debttracker.script;

class WaitingForCorrectCommandChatState extends WaitingForCommandChatState {
    private static final long serialVersionUID = 6006973408434400338L;
    private static final String WRONG_COMMAND_MESSAGE = "The command is unknown.";

    @Override
    public long getId() {
        return serialVersionUID;
    }

    WaitingForCorrectCommandChatState() {
        reply.add(0, WRONG_COMMAND_MESSAGE);
    }
}
