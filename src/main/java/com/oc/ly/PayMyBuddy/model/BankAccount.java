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
    @JoinColumn(name= "user")
    private User user;

    @Column(name= "rib")
    private String rib;

}
