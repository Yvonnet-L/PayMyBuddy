package com.oc.ly.PayMyBuddy.dto;

import com.oc.ly.PayMyBuddy.model.User;

public class BankAccountDTO {

    private Integer idBankAccount;
    private User user;
    private String rib;

    //------------------------------------------------------------------------------------------

    public BankAccountDTO() {
    }

    public BankAccountDTO(User user, String rib) {
        this.user = user;
        this.rib = rib;
    }

    //------------------------------------------------------------------------------------------

    public Integer getIdBankAccount() {
        return idBankAccount;
    }

    public void setIdBankAccount(Integer idBankAccount) {
        this.idBankAccount = idBankAccount;
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

    //------------------------------------------------------------------------------------------
}
