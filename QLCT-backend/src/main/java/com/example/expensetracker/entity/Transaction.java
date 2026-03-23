package com.example.expensetracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(precision = 10, scale = 2) // Giới hạn số thập phân cho MySQL
    private BigDecimal amount;

    private String category;
    private String type;        // INCOME hoặc EXPENSE
    private LocalDate date;

    @Column(name = "user_email", nullable = false)
    private String userEmail;
}