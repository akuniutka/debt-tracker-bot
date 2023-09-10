package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.Chat;
import dev.akuniutka.debttracker.entity.ChatState;

import java.util.ArrayList;
import java.util.List;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForStartChatState implements ChatState {
    private static final String START_COMMAND = "/start";
    private final DebtTrackerChatScript chatScript;

    WaitingForStartChatState(DebtTrackerChatScript chatScript) {
        this.chatScript = chatScript;
    }

    @Override
    public void processMessage(Chat chat, String message) {
        if (START_COMMAND.equals(message)) {
            chat.setChatState(chatScript.getChatState(WAITING_FOR_COMMAND));
        } else {
            chat.setChatState(chatScript.getChatState(WAITING_FOR_CORRECT_COMMAND));
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
