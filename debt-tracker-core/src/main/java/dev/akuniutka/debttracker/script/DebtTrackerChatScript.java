package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.ChatScript;
import dev.akuniutka.debttracker.entity.ChatState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

public class DebtTrackerChatScript implements ChatScript, Serializable {
    private final List<ChatState> chatStates = new ArrayList<>();
    private final DebtTrackerChatState initialChatState = WAITING_FOR_START;

    public DebtTrackerChatScript() {
        chatStates.add(new WaitingForStartChatState(this));
        chatStates.add(new WaitingForCommandChatState(this));
        chatStates.add(new WaitingForCorrectCommandChatState(this));
    }

    @Override
    public ChatState getInitialChatState() {
        return getChatState(initialChatState);
    }

    @Override
    public ChatState getChatState(int id) {
        return chatStates.get(id);
    }

    @Override
    public int getChatStateId(ChatState chatState) {
        for (int i = 0; i < chatStates.size(); i++) {
            if (chatStates.get(i) == chatState) {
                return i;
            }
        }
        throw new IllegalArgumentException("chat state not found");
    }

    ChatState getChatState(DebtTrackerChatState chatState) {
        return chatStates.get(chatState.getId());
    }
}
