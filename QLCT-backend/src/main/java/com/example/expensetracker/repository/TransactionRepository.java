package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Tự động generate câu lệnh: SELECT * FROM transactions WHERE user_email = ?
    List<Transaction> findByUserEmail(String userEmail);
}