package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Transaction;
import com.example.expensetracker.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    // API lấy thông tin user đăng nhập
    @GetMapping("/user")
    public Map<String, Object> getUserDetails(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return Map.of("error", "User not logged in");
        }
        return principal.getAttributes();
    }

    // API lấy danh sách giao dịch của user đó
    @GetMapping("/transactions")
    public List<Transaction> getUserTransactions(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            throw new RuntimeException("Unauthorized");
        }
        String email = principal.getAttribute("email");
        return transactionRepository.findByUserEmail(email);
    }

    // API thêm mới một giao dịch
    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction,
                                         @AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            throw new RuntimeException("Unauthorized");
        }
        String email = principal.getAttribute("email");
        transaction.setUserEmail(email); // Gắn email của user đang đăng nhập vào giao dịch
        return transactionRepository.save(transaction);
    }
}