package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.ChatScript;
import dev.akuniutka.debttracker.entity.ChatState;

import java.util.stream.Stream;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

public class DebtTrackerChatScript implements ChatScript {
    private final DebtTrackerChatState initialChatState = WAITING_FOR_START;

    public DebtTrackerChatScript() {}

    public DebtTrackerChatScript(EntryDao dao) {
        Stream.of(DebtTrackerChatState.values())
                .forEach(s -> s.getChatState().setDao(dao));
    }

    @Override
    public ChatState getInitialChatState() {
        return initialChatState.getChatState();
    }

    @Override
    public ChatState getChatState(long id) {
        return Stream.of(DebtTrackerChatState.values())
                .map(DebtTrackerChatState::getChatState)
                .filter(chatState -> id == chatState.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("chat state not found"));
    }

//    @Override
//    public int getChatStateId(ChatState chatState) {
//        return Stream.of(DebtTrackerChatState.values())
//                .filter(a -> chatState == a.getChatState())
//                .map(DebtTrackerChatState::ordinal)
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("chat state not found"));
//    }
}
