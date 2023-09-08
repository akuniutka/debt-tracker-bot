package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.ChatState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ChatStateConverter implements AttributeConverter<ChatState, String> {
    @Override
    public String convertToDatabaseColumn(ChatState chatState) {
        if (chatState == null) {
            return null;
        }
        return chatState.getCode();
    }

    @Override
    public ChatState convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(ChatState.values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
