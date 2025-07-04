package com.example.user_service.dao;

import com.example.user_service.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<Wallet, Integer> {
    Wallet findByUserId(Integer userId);
}
