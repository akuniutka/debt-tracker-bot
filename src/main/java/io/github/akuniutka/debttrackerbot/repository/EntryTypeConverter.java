package io.github.akuniutka.debttrackerbot.repository;

import io.github.akuniutka.debttrackerbot.entity.EntryType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EntryTypeConverter implements AttributeConverter<EntryType, String> {
    @Override
    public String convertToDatabaseColumn(EntryType entryType) {
        if (entryType == null) {
            return null;
        }
        return entryType.getCode();
    }

    @Override
    public EntryType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(EntryType.values())
                .filter(t -> t.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
