package dev.akuniutka.debttracker.entity;

import java.util.ArrayList;
import java.util.List;

public class ChatReply {
    private final List<String> reply;
    private final List<String> possibleAnswers;

    public ChatReply(List<String> reply, List<String> possibleAnswers) {
        this.reply = reply == null ? null : new ArrayList<>(reply);
        this.possibleAnswers = possibleAnswers == null ? null : new ArrayList<>(possibleAnswers);
    }

    public List<String> getReply() {
        return reply == null ? null : new ArrayList<>(reply);
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers == null ? null : new ArrayList<>(possibleAnswers);
    }
}
