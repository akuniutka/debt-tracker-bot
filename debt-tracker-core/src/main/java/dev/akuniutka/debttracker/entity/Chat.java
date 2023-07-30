package dev.akuniutka.debttracker.entity;

import dev.akuniutka.debttracker.dao.Dao;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
public class Chat {
    @Id
    private Long id;
    @Transient
    private Dao<Chat> dao;
    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapKey(name = "name")
    private Map<String, Account> accounts;

    public static Chat getChatByIdOrCreateNew(Long id, Dao<Chat> dao) {
        if (id == null && dao == null) {
            throw new IllegalArgumentException("Id and DAO object are null");
        } else if (id == null) {
            throw new IllegalArgumentException("Id is null");
        } else if (dao == null) {
            throw new IllegalArgumentException("DAO object is null");
        }
        Chat chat = dao.get(id).orElse(new Chat());
        chat.dao = dao;
        if (chat.id == null) {
            chat.id = id;
            chat.dao.save(chat);
        }
        return chat;
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

    protected Chat() {
        accounts = new HashMap<>();
    }
}
