package dev.akuniutka.debttracker.entity;

import dev.akuniutka.debttracker.dao.Dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Chat {
    @Id
    private Long id;
    @Transient
    private Dao<Chat> chatDao;

    public Chat(Long id, Dao<Chat> chatDao) {
        if (id == null && chatDao == null) {
            throw new IllegalArgumentException("Id and DAO object are null while creating Chat object");
        } else if (id == null) {
            throw new IllegalArgumentException("Id is null while creating Chat object");
        } else if (chatDao == null) {
            throw new IllegalArgumentException("DAO object is null while creating Chat object");
        }
        this.id = id;
        this.chatDao = chatDao;
        save();
    }

    public Long getId() {
        return id;
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

    protected Chat() {}

    private void save() {
        chatDao.save(this);
    }
}
