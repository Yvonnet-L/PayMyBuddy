package com.oc.ly.PayMyBuddy.dto;


import com.oc.ly.PayMyBuddy.model.User;

import java.time.LocalDate;

public class FriendDTO {

    private Integer idFriend;
    private User owner;
    private User friend;
    private LocalDate creationDate = LocalDate.now();

    //------------------------------------------------------------------------------------------

    public FriendDTO() {
    }

    public FriendDTO(User owner, User friend, LocalDate creationDate) {
            super();
            this.owner = owner;
            this.friend = friend;
            this.creationDate= creationDate;
        }

    //------------------------------------------------------------------------------------------

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

    //--------------------------------------------------------------------------

    @Override
    public String toString() {
        return "FriendDTO{" +
                "idFriend=" + idFriend +
                ", owner=" + owner +
                ", friend=" + friend +
                ", creationDate=" + creationDate +
                '}';
    }
}
