package com.oc.ly.PayMyBuddy.model;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name= "id_transfer", nullable = false)
    private int idTransfer;

    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    @Column(name= "rib", nullable = false)
    private String rib;

    @Column(name= "date", nullable = false)
    private LocalDate createDate = LocalDate.now();

    @Column(name= "amount", nullable = false)
    private double amount;

    @Column(name="type", columnDefinition = "enum('CREDIT_WALLET','DEBIT_WALLET')")
    private String type;

}
