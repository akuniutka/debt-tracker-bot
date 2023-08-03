package dev.akuniutka.debttracker.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ACTIVE_CHATS")
public class ActiveChat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CHAT_ID", nullable = false)
    private Long chatId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActiveChat that = (ActiveChat) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        return result;
    }
}
