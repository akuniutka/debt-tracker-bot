package dev.akuniutka.debttracker.script;

import dev.akuniutka.debttracker.entity.Chat;
import dev.akuniutka.debttracker.entity.ChatState;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractDebtTrackerChatState implements ChatState {
    protected final List<String> reply = new ArrayList<>();
    protected final List<String> possibleAnswers = new ArrayList<>();

    protected abstract DebtTrackerChatState nextChatState(Chat chat, String message);

    @Override
    public final void processMessage(Chat chat, String message) {
        chat.setChatState(nextChatState(chat, message).getChatState());
    }

    @Override
    public final List<String> getReply() {
        return reply.isEmpty() ? null : new ArrayList<>(reply);
    }

    @Override
    public final List<String> getPossibleAnswers() {
        return possibleAnswers.isEmpty() ? null : new ArrayList<>(possibleAnswers);
    }
}
