package com.oc.ly.PayMyBuddy.model;


import javax.persistence.*;

@Entity
@Table(name="bankAccount")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_bank_account", nullable = false)
    private Integer idBankAccount;

    @ManyToOne(optional = false)
    @JoinColumn(name= "user_id")
    private User user;

    @Column(name= "rib")
    private String rib;

    //------------------------------------------------------------------------------------------

    public BankAccount() {
    }

    public BankAccount(User user, String rib) {
        this.user = user;
        this.rib = rib;
    }

    public BankAccount(Integer idBankAccount, User user, String rib) {
        this.idBankAccount = idBankAccount;
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
