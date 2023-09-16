package dev.akuniutka.debttracker.script;

class WaitingForCorrectNameChatState extends WaitingForNameChatState {
    private static final long serialVersionUID = 7548707433111262157L;
    private static final String INCORRECT_NAME_MESSAGE = "Incorrect account's name.";

    @Override
    public long getId() {
        return serialVersionUID;
    }

    WaitingForCorrectNameChatState() {
        reply.add(0, INCORRECT_NAME_MESSAGE);
    }
}
