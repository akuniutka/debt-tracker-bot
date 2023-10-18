package io.github.akuniutka.debttrackerbot.repository;

import io.github.akuniutka.debttrackerbot.script.ChatState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ChatStateConverter implements AttributeConverter<ChatState, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ChatState chatState) {
        if (chatState == null) {
            return null;
        } else {
            return chatState.ordinal();
        }
    }

    @Override
    public ChatState convertToEntityAttribute(Integer id) {
        if (id == null) {
            return null;
        } else {
            return Stream.of(ChatState.values())
                    .filter(e -> id == e.ordinal())
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Chat state not found"));
        }
    }
}
