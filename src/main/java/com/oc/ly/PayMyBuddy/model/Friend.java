package com.oc.ly.PayMyBuddy.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Friend")
public class Friend {


        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name= "id_friend", nullable = false)
        private Integer friendID;

        @ManyToOne(optional = false)
        @JoinColumn(name= "owner")
        private User owner;

        @ManyToOne(optional = false)
        @JoinColumn(name= "friend")
        private User friend;

        @Column(name= "date_creation", updatable=false)
        private LocalDate dateCreation = LocalDate.now();

    public Friend() {
        super();
    }


    public Friend(Integer friendID, User owner, User friend, LocalDate dateCreation) {
        super();
        this.friendID = friendID;
        this.owner = owner;
        this.friend = friend;
        this.dateCreation = dateCreation;
    }


    public Friend(User owner, User friend, LocalDate dateCreation) {
        super();
        this.owner = owner;
        this.friend = friend;
        this.dateCreation = dateCreation;
    }


    public Integer getFriendID() {
        return friendID;
    }


    public void setFriendID(Integer friendID) {
        this.friendID = friendID;
    }


    public User getOwner() {
        return owner;
    }


    public void setOwner(User owner) {
        this.owner = owner;
    }


    public User getFriend() {
        return friend;
    }


    public void setFriend(User friend) {
        this.friend = friend;
    }


    public LocalDate getDateCreation() {
        return dateCreation;
    }


    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }


    @Override
    public String toString() {
        return "Friend [friendID=" + friendID + ", owner=" + owner + ", friend=" + friend + ", dateCreation="
                + dateCreation + "]";
    }



}
