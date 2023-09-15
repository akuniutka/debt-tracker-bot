package dev.akuniutka.debttracker.entity;

import java.io.Serializable;
import java.util.List;

public interface ChatState extends Serializable {
    long getId();

    void processMessage(Chat chat, String message);

    List<String> getReply();

    List<String> getPossibleAnswers();
}
