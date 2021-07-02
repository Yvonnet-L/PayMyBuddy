package com.oc.ly.PayMyBuddy.dto;

import com.oc.ly.PayMyBuddy.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransferDTO {

    private int idTransfer;
    private User user;
    private String rib;
    private LocalDateTime createDate;
    private double amount;
    private String type;

    //------------------------------------------------------------------------------------------

    public TransferDTO() {
    }

    //------------------------------------------------------------------------------------------

    public int getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(int idTransfer) {
        this.idTransfer = idTransfer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //------------------------------------------------------------------------------------------
}
