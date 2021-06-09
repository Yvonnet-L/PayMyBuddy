package com.oc.ly.PayMyBuddy.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String role;
    private String description;

    //-----------------------------------
    public Role() {
        super();
    }

    public Role(int id, String role, String description) {
        this.id = id;
        this.role = role;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}