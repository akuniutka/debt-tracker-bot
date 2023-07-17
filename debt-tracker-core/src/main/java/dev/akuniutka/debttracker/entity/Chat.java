package dev.akuniutka.debttracker.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CHAT")
public class Chat {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        return Objects.equals(id, chat.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
