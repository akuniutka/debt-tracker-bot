package dev.akuniutka.debttracker.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CHAT_ID", nullable = false)
    private Chat chat;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Entry> entries;

    public Account(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Account() {}
}
