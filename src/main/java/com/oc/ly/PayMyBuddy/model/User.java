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
    private Double  wallet  ;

    @Column(name= "creation_date", nullable = false)
    private LocalDate creationDate = LocalDate.now();

    @Column(name= "modif_date")
    private LocalDate modifDate = LocalDate.now();

    //---------------------------------------------------------------------

    public User() {
    }

    public User(int id, String userName, String firstName, String password, String email, boolean active, String roles, Double wallet, LocalDate creationDate, LocalDate modifDate) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.active = active;
        this.roles = roles;
        this.wallet = wallet;
        this.creationDate = creationDate;
        this.modifDate = modifDate;
    }

    public User(String userName, String firstName, String password, String email, boolean active, String roles, Double wallet, LocalDate creationDate, LocalDate modifDate) {
        this.userName = userName;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
        this.active = active;
        this.roles = roles;
        this.wallet = wallet;
        this.creationDate = creationDate;
        this.modifDate = modifDate;
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

    public LocalDate getModifDate() {
        return modifDate;
    }

    public void setModifDate(LocalDate modifDate) {
        this.modifDate = modifDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    //---------------------------------------------------------


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                ", wallet=" + wallet +
                ", creationDate=" + creationDate +
                ", modifDate=" + modifDate +
                '}';
    }
}
