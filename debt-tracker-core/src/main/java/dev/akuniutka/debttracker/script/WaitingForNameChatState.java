package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForNameChatState extends AbstractDebtTrackerChatState {
    private static final String MESSAGE_FOR_USER = "please enter account's name";
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
            entryService.updateDraft(chat.getUserId(), message);
            return WAITING_FOR_COMMAND;
        }
    }
}
