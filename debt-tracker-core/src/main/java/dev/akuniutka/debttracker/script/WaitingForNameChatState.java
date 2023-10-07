package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;

import java.util.Optional;

import static dev.akuniutka.debttracker.script.DebtTrackerChatState.*;

class WaitingForNameChatState extends AbstractDebtTrackerChatState {
    private static final String MESSAGE_FOR_USER = "please enter account's name";

    WaitingForNameChatState() {
        reply.add(MESSAGE_FOR_USER);
    }

    @Override
    protected DebtTrackerChatState nextChatState(Chat chat, String message) {
        Optional<Command> command = Command.getCommand(message);
        if (command.isPresent() && command.get() == Command.CANCEL) {
            entryService.dropDraft(chat.getUserId());
        } else {
            entryService.updateDraft(chat.getUserId(), message);
        }
        return WAITING_FOR_COMMAND;
    }
}
