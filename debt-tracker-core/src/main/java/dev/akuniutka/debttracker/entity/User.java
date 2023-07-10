package dev.akuniutka.debttracker.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CHAT_ID", nullable = false)
    private Long chatId;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "LAST_MESSAGE_AT", nullable = false)
    private OffsetDateTime lastMessageAt;

    public User(Long chatId) {
        this.chatId = chatId;
    }
}
