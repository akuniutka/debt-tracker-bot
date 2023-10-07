package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.debttracker.exception.WrongAmountException;

import java.math.BigDecimal;
import java.util.Optional;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForAmountChatState extends AbstractDebtTrackerChatState {
    private static final String MESSAGE_FOR_USER = "please enter an amount";

    WaitingForAmountChatState() {
        reply.add(MESSAGE_FOR_USER);
    }

    @Override
    protected DebtTrackerChatState nextChatState(Chat chat, String message) {
        Optional<Command> command = Command.getCommand(message);
        if (command.isPresent() && command.get() == Command.CANCEL) {
            entryService.dropDraft(chat.getUserId());
            return WAITING_FOR_COMMAND;
        } else {
            try {
                BigDecimal amount = new BigDecimal(message);
                entryService.updateDraft(chat.getUserId(), amount);
                return WAITING_FOR_NAME;
            } catch (NumberFormatException | WrongAmountException e) {
                return WAITING_FOR_CORRECT_AMOUNT;
            }
        }
    }
}
