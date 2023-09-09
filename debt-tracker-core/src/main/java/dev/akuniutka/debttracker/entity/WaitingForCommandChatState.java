package dev.akuniutka.debttracker.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaitingForCommandChatState implements ChatState {
    private static final String MESSAGE_FOR_USER = "Please, enter the command.";
    private static final String BORROWED_COMMAND = "/borrowed";
    private static final String LENT_COMMAND = "/lent";
    private static final String REPAID_COMMAND = "/repaid";
    private static final String GOT_BACK_COMMAND = "/gotBack";
    private static final String SHOW_CURRENT_STATUS = "/showCurrentStatus";

    @Override
    public void processMessage(Chat chat, String message) {
        if (BORROWED_COMMAND.equals(message)) {
            chat.setChatState(new WaitingForCommandChatState());
        } else if (LENT_COMMAND.equals(message)) {
            chat.setChatState(new WaitingForCommandChatState());
        } else if (REPAID_COMMAND.equals(message)) {
            chat.setChatState(new WaitingForCommandChatState());
        } else if (GOT_BACK_COMMAND.equals(message)) {
            chat.setChatState(new WaitingForCommandChatState());
        } else if (SHOW_CURRENT_STATUS.equals(message)) {
            chat.setChatState(new WaitingForCommandChatState());
        } else {
            chat.setChatState(new WaitingForCorrectCommandChatState());
        }
    }

    @Override
    public List<String> getReply() {
        List<String> reply = new ArrayList<>();
        reply.add(MESSAGE_FOR_USER);
        return reply;
    }

    @Override
    public List<String> getPossibleAnswers() {
        return Arrays.asList(BORROWED_COMMAND, LENT_COMMAND, REPAID_COMMAND, GOT_BACK_COMMAND, SHOW_CURRENT_STATUS);
    }
}
