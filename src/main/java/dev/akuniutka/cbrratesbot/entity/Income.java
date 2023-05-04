package dev.akuniutka.cbrratesbot.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "INCOMES")
public class Income {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CHAT_ID")
    private Long chatId;
    @Column(name = "INCOME")
    private BigDecimal income;
}
