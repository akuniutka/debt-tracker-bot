package dev.akuniutka.cbrratesbot.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "INCOMES")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CHAT_ID", nullable = false)
    private Long chatId;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal value;

    @Column(name = "ENTRY_DATE", nullable = false)
    private Date entryDate;

}
