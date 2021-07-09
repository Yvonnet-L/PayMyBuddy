package com.oc.ly.PayMyBuddy.dto;

import java.time.LocalDate;

public class UserDTO {

    private int id;
    private String userName;
    private String firstName;
    private String password;
    private String email;
    private boolean active;
    private String roles;
    private Double wallet;
    private LocalDate creationDate;
    private LocalDate modifDate;

    //------------------------------------------------------------------------------------------
    public UserDTO() {
    }

    public UserDTO(String userName, String firstName, String password, String email) {
        this.userName = userName;
        this.firstName = firstName;
        this.password = password;
        this.email = email;
    }
    //------------------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Double getWallet() {
        return wallet;
    }

    public void setWallet(Double wallet) {
        this.wallet = wallet;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getModifDate() {
        return modifDate;
    }

    public void setModifDate(LocalDate modifDate) {
        this.modifDate = modifDate;
    }

    //---------------------------------------------------------

    @Override
    public String toString() {
        return "UserDTO{" +
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


    //---------------------------------------------------------
}
