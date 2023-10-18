package io.github.akuniutka.debttrackerbot.script;

import io.github.akuniutka.debttrackerbot.entity.Chat;
import io.github.akuniutka.debttrackerbot.exception.WrongAmountException;

import java.math.BigDecimal;
import java.util.Optional;

class WaitingForAmountState extends AbstractState {
    @Override
    public void addUserMessage(Chat chat, String message) {
        Optional<Command> command = Command.getCommand(message);
        if (command.isPresent()) {
            switch (command.get()) {
                case STATUS:
                    chat.sendMessageToUser(getCurrentDebtsReport(chat));
                case START:
                case CANCEL:
                    entryService.dropDraft(chat.getUserId());
                    chat.sendMessageToUser(ENTER_COMMAND);
                    chat.setState(ChatState.WAITING_FOR_COMMAND);
                    break;
                case BORROWED:
                case REPAID:
                case LENT:
                case REDEEMED:
                    entryService.updateDraft(chat.getUserId(), entryTypeFromCommand(command.get()));
                    chat.sendMessageToUser(ENTER_AMOUNT);
                    break;
                case HELP:
                case SETTINGS:
                default:
                    throw new RuntimeException("Not implemented yet");
            }
        } else {
            try {
                BigDecimal amount = new BigDecimal(message);
                entryService.updateDraft(chat.getUserId(), amount);
                chat.sendMessageToUser(ENTER_ACCOUNT);
                chat.setState(ChatState.WAITING_FOR_NAME);
            } catch (NumberFormatException | WrongAmountException e) {
                chat.sendMessageToUser(WRONG_AMOUNT);
                chat.sendMessageToUser(ENTER_AMOUNT);
            }
        }
    }
}
