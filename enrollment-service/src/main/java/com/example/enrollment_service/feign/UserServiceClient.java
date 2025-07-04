package com.example.enrollment_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "user-service")
public interface UserServiceClient {


    @GetMapping("/user/wallet/by-user/{userId}")
    Integer getWalletIdByUserId(@PathVariable("userId") Integer userId);

    @GetMapping("/user/wallet/{walletId}/balance")
    Integer getWalletBalance(@PathVariable("walletId") Integer walletId);

    @PostMapping("/user/wallet/{walletId}/update-balance")
    void updateWalletBalance(@PathVariable("walletId") Integer walletId, @org.springframework.web.bind.annotation.RequestParam int amount);




}


