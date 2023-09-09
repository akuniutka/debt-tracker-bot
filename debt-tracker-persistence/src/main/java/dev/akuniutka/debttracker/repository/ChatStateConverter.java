package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.ChatState;
import dev.akuniutka.debttracker.entity.WaitingForCommandChatState;
import dev.akuniutka.debttracker.entity.WaitingForCorrectCommandChatState;
import dev.akuniutka.debttracker.entity.WaitingForStartChatState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ChatStateConverter implements AttributeConverter<ChatState, String> {
    @Override
    public String convertToDatabaseColumn(ChatState chatState) {
        if (chatState == null) {
            return null;
        } else if (chatState instanceof WaitingForStartChatState) {
            return "A";
        } else if (chatState instanceof WaitingForCorrectCommandChatState) {
            return "C";
        } else if (chatState instanceof WaitingForCommandChatState) {
            return "B";
        } else {
            throw new IllegalArgumentException("unknown chat state type");
        }
    }

    @Override
    public ChatState convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        } else if ("A".equals(code)) {
            return new WaitingForStartChatState();
        } else if ("B".equals(code)) {
            return new WaitingForCommandChatState();
        } else if ("C".equals(code)) {
            return new WaitingForCorrectCommandChatState();
        } else {
            throw new IllegalArgumentException("unknown code for chat state");
        }
    }
}
