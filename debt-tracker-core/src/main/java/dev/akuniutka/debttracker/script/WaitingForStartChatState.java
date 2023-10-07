package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;

import java.util.Optional;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForStartChatState extends AbstractDebtTrackerChatState {
    @Override
    protected DebtTrackerChatState nextChatState(Chat chat, String message) {
        Optional<Command> command = Command.getCommand(message);
        if (command.isPresent() && command.get() == Command.START) {
            return WAITING_FOR_COMMAND;
        } else {
            return WAITING_FOR_CORRECT_COMMAND;
        }
    }
}
