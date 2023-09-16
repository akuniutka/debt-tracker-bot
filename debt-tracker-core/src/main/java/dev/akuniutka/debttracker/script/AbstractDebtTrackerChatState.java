package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.core.ChatState;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractDebtTrackerChatState implements ChatState {
    protected final List<String> reply = new ArrayList<>();
    protected final List<String> possibleAnswers = new ArrayList<>();
    protected transient EntryDao dao;

    protected abstract DebtTrackerChatState nextChatState(Chat chat, String message);

    @Override
    public final void processMessage(Chat chat, String message) {
        chat.setState(nextChatState(chat, message).getChatState());
    }

    @Override
    public final List<String> getReply() {
        return reply.isEmpty() ? null : new ArrayList<>(reply);
    }

    @Override
    public final List<String> getPossibleAnswers() {
        return possibleAnswers.isEmpty() ? null : new ArrayList<>(possibleAnswers);
    }

    public void setDao(EntryDao dao) {
        this.dao = dao;
    }
}
