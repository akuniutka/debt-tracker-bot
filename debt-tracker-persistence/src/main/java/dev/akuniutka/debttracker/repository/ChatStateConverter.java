package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.ChatScript;
import dev.akuniutka.debttracker.entity.ChatState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ChatStateConverter implements AttributeConverter<ChatState, Long> {
    private final ChatScript chatScript;

    public ChatStateConverter(ChatScript chatScript) {
        this.chatScript = chatScript;
    }

    @Override
    public Long convertToDatabaseColumn(ChatState chatState) {
        if (chatState == null) {
            return null;
        } else {
            return chatState.getId();
        }
    }

    @Override
    public ChatState convertToEntityAttribute(Long id) {
        if (id == null) {
            return null;
        } else {
            return chatScript.getChatState(id);
        }
    }
}
