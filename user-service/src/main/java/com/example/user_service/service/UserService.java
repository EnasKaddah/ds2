package com.example.user_service.service;

import com.example.user_service.dao.UserDao;
import com.example.user_service.dao.WalletDao;
import com.example.user_service.model.User;
import com.example.user_service.model.UserWrapper;
import com.example.user_service.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    WalletDao walletDao;


    public ResponseEntity<String> register(String name , String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRole("student");
        userDao.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser_id(user.getId());
        wallet.setBalance(100000);
        walletDao.save(wallet);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<String> addTrainer(String name , String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setRole("trainer");
        userDao.save(user);

        Wallet wallet = new Wallet();
        wallet.setUser_id(user.getId());
        wallet.setBalance(100000);
        walletDao.save(wallet);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<UserWrapper> getById(Integer id) {
        User user = userDao.findById(id).get();

        UserWrapper wrapper = new UserWrapper();
        wrapper.setId(user.getId());
        wrapper.setName(user.getName());


        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    public Wallet getWalletById(Integer walletId) {
        return walletDao.findById(walletId).orElse(null);
    }

    public boolean updateWalletBalance(Integer walletId, int amount) {
        Wallet wallet = walletDao.findById(walletId).orElse(null);
        if (wallet == null) {
            return false;
        }
        wallet.setBalance(wallet.getBalance() + amount);
        walletDao.save(wallet);
        return true;
    }

    public Wallet getWalletByUserId(Integer userId) {
        return walletDao.findByUserId(userId);
    }







}
