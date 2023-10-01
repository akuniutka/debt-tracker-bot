package dev.akuniutka.debttracker.script;

class WaitingForCorrectCommandChatState extends WaitingForCommandChatState {
    private static final String WRONG_COMMAND_MESSAGE = "an unknown command";

    WaitingForCorrectCommandChatState() {
        reply.add(0, WRONG_COMMAND_MESSAGE);
    }
}
