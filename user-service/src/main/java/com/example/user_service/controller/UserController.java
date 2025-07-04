package com.example.user_service.controller;


import com.example.user_service.model.User;
import com.example.user_service.model.UserDto;
import com.example.user_service.model.UserWrapper;
import com.example.user_service.model.Wallet;
import com.example.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        return userService.register(userDto.getName() , userDto.getPassword());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<UserWrapper> getById(@PathVariable Integer id){
        return userService.getById(id);
    }

    @PostMapping("/add-trainer")
    public ResponseEntity<String> addTrainer(@RequestBody UserDto trainerDto) {
        return userService.addTrainer(trainerDto.getName(), trainerDto.getPassword());
    }

    @GetMapping("/wallet/{walletId}/balance")
    public ResponseEntity<Integer> getWalletBalance(@PathVariable Integer walletId) {
        Wallet wallet = userService.getWalletById(walletId);
        if (wallet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wallet.getBalance());
    }
    @PostMapping("/wallet/{walletId}/update-balance")
    public ResponseEntity<Void> updateWalletBalance(@PathVariable Integer walletId, @RequestParam int amount) {
        boolean updated = userService.updateWalletBalance(walletId, amount);
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/wallet/by-user/{userId}")
    public ResponseEntity<Integer> getWalletIdByUserId(@PathVariable Integer userId) {
        com.example.user_service.model.Wallet wallet = userService.getWalletByUserId(userId);
        if (wallet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(wallet.getId());
    }

}
