package io.github.akuniutka.debttrackerbot.script;

import io.github.akuniutka.debttrackerbot.entity.Chat;

import java.util.Optional;

class WaitingForCommandState extends AbstractState {
    @Override
    public void addUserMessage(Chat chat, String message) {
        Optional<Command> command = Command.getCommand(message);
        if (command.isPresent()) {
            switch (command.get()) {
                case START:
                case CANCEL:
                    chat.sendMessageToUser(ENTER_COMMAND);
                    break;
                case BORROWED:
                case REPAID:
                case LENT:
                case REDEEMED:
                    entryService.updateDraft(chat.getUserId(), entryTypeFromCommand(command.get()));
                    chat.sendMessageToUser(ENTER_AMOUNT);
                    chat.setState(ChatState.WAITING_FOR_AMOUNT);
                    break;
                case STATUS:
                    chat.sendMessageToUser(getCurrentDebtsReport(chat));
                    chat.sendMessageToUser(ENTER_COMMAND);
                    break;
                case HELP:
                case SETTINGS:
                default:
                    throw new RuntimeException("Not implemented yet");
            }
        } else {
            chat.sendMessageToUser(UNKNOWN_COMMAND);
            chat.sendMessageToUser(ENTER_COMMAND);
        }
    }
}
