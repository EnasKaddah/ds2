package com.example.user_service.model;


import lombok.Data;

@Data
public class UserDto {
    public String name;
    public  String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
