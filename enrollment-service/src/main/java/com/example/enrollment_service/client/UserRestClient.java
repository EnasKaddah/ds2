package com.example.enrollment_service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Component
public class UserRestClient {
    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetWalletIdByUserId")
    public Integer getWalletIdByUserId(Integer userId) {
        return restTemplate.getForObject("http://user-service/user/wallet/by-user/" + userId, Integer.class);
    }


    public Integer fallbackGetWalletIdByUserId(Integer userId, Throwable t) {
        System.err.println("Fallback for getWalletIdByUserId: " + t.getMessage());
        return -1;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackGetWalletBalance")
    public Integer getWalletBalance(Integer walletId) {
        return restTemplate.getForObject("http://user-service/user/wallet/" + walletId + "/balance", Integer.class);
    }

    public Integer fallbackGetWalletBalance(Integer walletId, Throwable t) {
        System.err.println("Fallback for getWalletBalance: " + t.getMessage());
        return -1;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackUpdateWalletBalance")
    public void updateWalletBalance(Integer walletId, int amount) {
        restTemplate.postForObject("http://user-service/user/wallet/" + walletId + "/update-balance?amount=" + amount, null, Void.class);
    }

    public void fallbackUpdateWalletBalance(Integer walletId, int amount, Throwable t) {
        System.err.println("Fallback for updateWalletBalance: " + t.getMessage());
        // Optionally log or handle the failure
    }
} 