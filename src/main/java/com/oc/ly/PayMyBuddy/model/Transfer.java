package com.oc.ly.PayMyBuddy.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name= "amount", nullable = false)
    private double amount;

    @Column(name="type", columnDefinition = "enum('CREDIT_WALLET','DEBIT_WALLET')")
    private String type;


  //----------------------------------------------------------------------------------
    public Transfer() {
    }

    public Transfer(User user, String rib, LocalDateTime createDate, double amount, String type) {
        this.user = user;
        this.rib = rib;
        this.createDate = createDate;
        this.amount = amount;
        this.type = type;
    }
    //----------------------------------------------------------------------------------

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
}
