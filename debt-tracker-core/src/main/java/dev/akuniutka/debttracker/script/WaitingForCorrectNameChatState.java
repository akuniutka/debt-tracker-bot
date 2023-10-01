package dev.akuniutka.debttracker.script;

class WaitingForCorrectNameChatState extends WaitingForNameChatState {
    private static final String INCORRECT_NAME_MESSAGE = "invalid account's name";

    WaitingForCorrectNameChatState() {
        reply.add(0, INCORRECT_NAME_MESSAGE);
    }
}
