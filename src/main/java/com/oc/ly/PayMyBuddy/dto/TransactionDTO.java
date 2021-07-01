package com.oc.ly.PayMyBuddy.dto;

import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;
import com.oc.ly.PayMyBuddy.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionDTO {

    private int idTransaction;
    private User payer;
    private User beneficiary;
    private LocalDateTime creationDate;
    private double amount;
    private String description;
    private double fee;

    //---------------------------------------------------------

    public TransactionDTO() {
    }
/*
    public TransactionDTO(int idTransaction, User payer, User beneficiary, LocalDateTime creationDate, double amount, String description, double fee) {
        this.idTransaction = idTransaction;
        this.payer = payer;
        this.beneficiary = beneficiary;
        this.creationDate = creationDate;
        this.amount = amount;
        this.description = description;
        this.fee = fee;
    }

    public TransactionDTO(User payer, User beneficiary, LocalDateTime creationDate, double amount, String description, double fee) {
        this.payer = payer;
        this.beneficiary = beneficiary;
        this.creationDate = creationDate;
        this.amount = amount;
        this.description = description;
        this.fee = fee;
    }
*/
    public TransactionDTO(User payer, User beneficiary, double amount, String description) {
        this.payer = payer;
        this.beneficiary = beneficiary;
        this.amount = amount;
        this.description = description;
    }
//---------------------------------------------------------

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(User beneficiary) {
        this.beneficiary = beneficiary;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
            this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    //---------------------------------------------------------

}
