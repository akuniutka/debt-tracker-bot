package dev.akuniutka.cbrratesbot.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ACTIVE_CHATS")
public class ActiveChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CHAT_ID", nullable = false)
    private Long chatId;

}
