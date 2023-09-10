package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.Chat;
import dev.akuniutka.debttracker.entity.ChatState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForCommandChatState implements ChatState {
    private static final String MESSAGE_FOR_USER = "Please, enter the command.";
    private static final String BORROWED_COMMAND = "/borrowed";
    private static final String LENT_COMMAND = "/lent";
    private static final String REPAID_COMMAND = "/repaid";
    private static final String GOT_BACK_COMMAND = "/gotBack";
    private static final String SHOW_CURRENT_STATUS = "/showCurrentStatus";
    private final DebtTrackerChatScript chatScript;

    WaitingForCommandChatState(DebtTrackerChatScript chatScript) {
        this.chatScript = chatScript;
    }

    @Override
    public void processMessage(Chat chat, String message) {
        if (BORROWED_COMMAND.equals(message)) {
            chat.setChatState(chatScript.getChatState(WAITING_FOR_COMMAND));
        } else if (LENT_COMMAND.equals(message)) {
            chat.setChatState(chatScript.getChatState(WAITING_FOR_COMMAND));
        } else if (REPAID_COMMAND.equals(message)) {
            chat.setChatState(chatScript.getChatState(WAITING_FOR_COMMAND));
        } else if (GOT_BACK_COMMAND.equals(message)) {
            chat.setChatState(chatScript.getChatState(WAITING_FOR_COMMAND));
        } else if (SHOW_CURRENT_STATUS.equals(message)) {
            chat.setChatState(chatScript.getChatState(WAITING_FOR_COMMAND));
        } else {
            chat.setChatState(chatScript.getChatState(WAITING_FOR_CORRECT_COMMAND));
        }
    }

    @Override
    public List<String> getReply() {
        List<String> reply = new ArrayList<>();
        reply.add(MESSAGE_FOR_USER);
        return reply;
    }

    @Override
    public List<String> getPossibleAnswers() {
        return Arrays.asList(BORROWED_COMMAND, LENT_COMMAND, REPAID_COMMAND, GOT_BACK_COMMAND, SHOW_CURRENT_STATUS);
    }
}
