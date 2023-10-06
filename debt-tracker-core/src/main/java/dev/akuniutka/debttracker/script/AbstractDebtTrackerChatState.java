package dev.akuniutka.debttracker.script;

import dev.akuniutka.chatbot.core.Chat;
import dev.akuniutka.chatbot.core.ChatState;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractDebtTrackerChatState implements ChatState {
    protected final List<String> reply = new ArrayList<>();
    protected final List<String> possibleAnswers = new ArrayList<>();
    protected transient EntryService entryService;

    protected abstract DebtTrackerChatState nextChatState(Chat chat, String message);

    @Override
    public final void processMessage(Chat chat, String message) {
        chat.setState(nextChatState(chat, message).getChatState());
    }

    @Override
    public final List<String> getReply(Chat chat) {
        return reply.isEmpty() ? null : new ArrayList<>(reply);
    }

    public void setEntryService(EntryService entryService) {
        this.entryService = entryService;
    }
}
