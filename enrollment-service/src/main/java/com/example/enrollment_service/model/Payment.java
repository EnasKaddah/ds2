package com.example.enrollment_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer studentWalletId;
    private Integer trainerWalletId;
    private Double amount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStudentWalletId() {
        return studentWalletId;
    }

    public void setStudentWalletId(Integer studentWalletId) {
        this.studentWalletId = studentWalletId;
    }

    public Integer getTrainerWalletId() {
        return trainerWalletId;
    }

    public void setTrainerWalletId(Integer trainerWalletId) {
        this.trainerWalletId = trainerWalletId;
    }
}