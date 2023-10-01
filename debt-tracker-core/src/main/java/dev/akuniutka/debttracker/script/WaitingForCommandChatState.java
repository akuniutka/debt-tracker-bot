package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.debttracker.entity.EntryType;

import java.util.Arrays;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForCommandChatState extends AbstractDebtTrackerChatState {
    private static final String MESSAGE_FOR_USER = "please enter a command";
    private static final String BORROWED_COMMAND = "/borrowed";
    private static final String LENT_COMMAND = "/lent";
    private static final String REPAID_COMMAND = "/repaid";
    private static final String GOT_BACK_COMMAND = "/gotBack";
    private static final String SHOW_CURRENT_STATUS_COMMAND = "/showCurrentStatus";

    WaitingForCommandChatState() {
        reply.add(MESSAGE_FOR_USER);
        possibleAnswers.addAll(
                Arrays.asList(BORROWED_COMMAND, LENT_COMMAND, REPAID_COMMAND, GOT_BACK_COMMAND, SHOW_CURRENT_STATUS_COMMAND)
        );
    }

    @Override
    protected DebtTrackerChatState nextChatState(Chat chat, String message) {
        if (BORROWED_COMMAND.equals(message)) {
            entryService.updateDraft(chat.getUserId(), EntryType.BORROWED);
            return WAITING_FOR_AMOUNT;
        } else if (LENT_COMMAND.equals(message)) {
            entryService.updateDraft(chat.getUserId(), EntryType.LENT);
            return WAITING_FOR_AMOUNT;
        } else if (REPAID_COMMAND.equals(message)) {
            entryService.updateDraft(chat.getUserId(), EntryType.REPAID);
            return WAITING_FOR_AMOUNT;
        } else if (GOT_BACK_COMMAND.equals(message)) {
            entryService.updateDraft(chat.getUserId(), EntryType.GOT_BACK);
            return WAITING_FOR_AMOUNT;
        } else if (SHOW_CURRENT_STATUS_COMMAND.equals(message)) {
            return SHOWING_CURRENT_STATUS;
        } else {
            return WAITING_FOR_CORRECT_COMMAND;
        }
    }
}
