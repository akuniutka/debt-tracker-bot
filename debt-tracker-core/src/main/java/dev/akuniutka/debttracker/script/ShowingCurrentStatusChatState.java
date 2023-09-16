package dev.akuniutka.debttracker.script;

class ShowingCurrentStatusChatState extends WaitingForCommandChatState {
    private static final long serialVersionUID = 517562733486492251L;

    @Override
    public long getId() {
        return serialVersionUID;
    }

    ShowingCurrentStatusChatState() {
        reply.add(0, "not implemented yet");
    }
}
