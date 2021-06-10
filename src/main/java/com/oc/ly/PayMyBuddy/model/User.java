package com.oc.ly.PayMyBuddy.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_user", nullable = false)
    private int id;

    @Column(name= "user_name", length = 40, nullable = false)
    private String userName;

    @Column(name= "firstname", length = 40)
    private String firstName;

    @Column(name= "password", length = 80, nullable = false)
    private String password;

    @Column(name= "email", length = 40, unique = true, nullable = false)
    private String email;

    @Column(name= "active")
    private boolean active;

    @Column(name= "roles", length = 20, nullable = false)
    private String roles;

    @Column(name= "wallet", length = 10)
    private Double wallet;

    @Column(name= "creation_date", nullable = false)
    private LocalDate creationDate = LocalDate.now();

    @Column(name= "modif_date", nullable = false)
    private LocalDate modifDate = LocalDate.now();

    //---------------------------------------------------------------------

    public User() {
    }

    //---------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.creationDate = createDate;
    }

    public LocalDate getModifDate() {
        return modifDate;
    }

    public void setModifDate(LocalDate modifDate) {
        this.modifDate = modifDate;
    }
}
