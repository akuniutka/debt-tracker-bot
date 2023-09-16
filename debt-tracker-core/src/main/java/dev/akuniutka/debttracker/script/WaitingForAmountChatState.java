package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForAmountChatState extends AbstractDebtTrackerChatState {
    private static final String MESSAGE_FOR_USER = "Please, enter the amount.";
    private static final String CANCEL_COMMAND = "/cancel";

    WaitingForAmountChatState() {
        reply.add(MESSAGE_FOR_USER);
        possibleAnswers.add(CANCEL_COMMAND);
    }

    @Override
    protected DebtTrackerChatState nextChatState(Chat chat, String message) {
        if (CANCEL_COMMAND.equals(message)) {
            return WAITING_FOR_COMMAND;
        } else {
            return WAITING_FOR_NAME;
        }
    }
}
