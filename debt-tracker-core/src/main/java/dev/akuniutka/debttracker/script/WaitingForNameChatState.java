package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.Chat;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForNameChatState extends AbstractDebtTrackerChatState {
    private static final long serialVersionUID = 72894833554232562L;
    private static final String MESSAGE_FOR_USER = "Please, enter account's name.";
    private static final String CANCEL_COMMAND = "/cancel";

    WaitingForNameChatState() {
        reply.add(MESSAGE_FOR_USER);
        possibleAnswers.add(CANCEL_COMMAND);
    }

    @Override
    protected DebtTrackerChatState nextChatState(Chat chat, String message) {
        if (message.isEmpty()) {
            return WAITING_FOR_CORRECT_NAME;
        } else {
            return WAITING_FOR_COMMAND;
        }
    }
}
