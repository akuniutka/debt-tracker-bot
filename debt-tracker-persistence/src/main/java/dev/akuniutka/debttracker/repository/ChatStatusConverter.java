package dev.akuniutka.debttracker.repository;

import dev.akuniutka.debttracker.entity.ChatStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ChatStatusConverter implements AttributeConverter<ChatStatus, String> {
    @Override
    public String convertToDatabaseColumn(ChatStatus chatStatus) {
        if (chatStatus == null) {
            return null;
        }
        return chatStatus.getCode();
    }

    @Override
    public ChatStatus convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(ChatStatus.values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
