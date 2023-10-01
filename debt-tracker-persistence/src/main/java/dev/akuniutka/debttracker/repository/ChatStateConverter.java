package dev.akuniutka.debttracker.repository;

import dev.akuniutka.chatbot.core.ChatState;
import dev.akuniutka.debttracker.script.DebtTrackerChatState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ChatStateConverter implements AttributeConverter<ChatState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ChatState chatState) {
        if (chatState == null) {
            return null;
        } else {
            return DebtTrackerChatState.getChatStateId(chatState);
        }
    }

    @Override
    public ChatState convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        } else {
            return DebtTrackerChatState.getChatState(id);
        }
    }
}
