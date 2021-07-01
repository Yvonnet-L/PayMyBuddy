package com.oc.ly.PayMyBuddy.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="Friend")
public class Friend {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_friend", nullable = false)
    private Integer idFriend;

    @ManyToOne(optional = false)
    @JoinColumn(name= "owner_id")
    private User owner;

    @ManyToOne(optional = false)
    @JoinColumn(name= "friend_id")
    private User friend;

    @Column(name= "creation_date", updatable=false)
    private LocalDate creationDate = LocalDate.now();

    //--------------------------------------------------------------------------

    public Friend() {
        super();
    }
/*
    public Friend(User owner, User friend, LocalDate creationDate) {
        this.owner = owner;
        this.friend = friend;
        this.creationDate = creationDate;
    }

    public Friend(Integer idFriend, User owner, User friend, LocalDate creationDate) {
        this.idFriend = idFriend;
        this.owner = owner;
        this.friend = friend;
        this.creationDate = creationDate;
    }


 */
//--------------------------------------------------------------------------

    public Integer getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(Integer idFriend) {
        this.idFriend = idFriend;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
