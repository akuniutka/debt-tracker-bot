package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.Chat;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForStartChatState extends AbstractDebtTrackerChatState {
    private static final String START_COMMAND = "/start";

    WaitingForStartChatState() {
        possibleAnswers.add(START_COMMAND);
    }

    @Override
    protected DebtTrackerChatState nextChatState(Chat chat, String message) {
        if (START_COMMAND.equals(message)) {
            return WAITING_FOR_COMMAND;
        } else {
            return WAITING_FOR_CORRECT_COMMAND;
        }
    }
}
