package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.debttracker.entity.EntryType;

import java.util.Optional;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForCommandChatState extends AbstractDebtTrackerChatState {
    private static final String MESSAGE_FOR_USER = "please enter a command";

    WaitingForCommandChatState() {
        reply.add(MESSAGE_FOR_USER);
    }

    @Override
    protected DebtTrackerChatState nextChatState(Chat chat, String message) {
        Optional<Command> command = Command.getCommand(message);
        if (command.isPresent()) {
            switch (command.get()) {
                case START:
                case CANCEL:
                    return WAITING_FOR_COMMAND;
                case BORROWED:
                    entryService.updateDraft(chat.getUserId(), EntryType.BORROWED);
                    return WAITING_FOR_AMOUNT;
                case REPAID:
                    entryService.updateDraft(chat.getUserId(), EntryType.REPAID);
                    return WAITING_FOR_AMOUNT;
                case LENT:
                    entryService.updateDraft(chat.getUserId(), EntryType.LENT);
                    return WAITING_FOR_AMOUNT;
                case REDEEMED:
                    entryService.updateDraft(chat.getUserId(), EntryType.GOT_BACK);
                    return WAITING_FOR_AMOUNT;
                case STATUS:
                    return SHOWING_CURRENT_STATUS;
                case HELP:
                case SETTINGS:
                    throw new RuntimeException("Not implemented yet");
                default:
                    return WAITING_FOR_CORRECT_COMMAND;
            }
        } else {
            return WAITING_FOR_CORRECT_COMMAND;
        }
    }
}
