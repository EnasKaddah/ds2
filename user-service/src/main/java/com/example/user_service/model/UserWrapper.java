package com.example.user_service.model;

import lombok.Data;

@Data

public class UserWrapper {
    private Integer id;

    public String name;

    public Integer balance;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
