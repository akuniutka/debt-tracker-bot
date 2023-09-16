package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.ChatScript;
import dev.akuniutka.chatbot.core.ChatState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ChatStateConverter implements AttributeConverter<ChatState, Integer> {
    private final ChatScript chatScript;

    public ChatStateConverter(ChatScript chatScript) {
        this.chatScript = chatScript;
    }

    @Override
    public Integer convertToDatabaseColumn(ChatState chatState) {
        if (chatState == null) {
            return null;
        } else {
            return chatScript.getChatStateId(chatState);
        }
    }

    @Override
    public ChatState convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        } else {
            return chatScript.getChatState(id);
        }
    }
}
