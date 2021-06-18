package com.oc.ly.PayMyBuddy.dto;


import com.oc.ly.PayMyBuddy.model.User;

import java.time.LocalDate;

public class FriendDTO {


        private Integer idFriend;

        private User owner;

        private User friend;

        private LocalDate dateCreation = LocalDate.now();


        //--------------------------------------------------------------------------


    public FriendDTO() {
    }

    public FriendDTO(User owner, User friend, LocalDate dateCreation) {
            super();
            this.owner = owner;
            this.friend = friend;
            this.dateCreation = dateCreation;
        }


        public FriendDTO(Integer idFriend, User owner, User friend) {
            super();
            this.idFriend = idFriend;
            this.owner = owner;
            this.friend = friend;
        }

    public FriendDTO(Integer idFriend, User owner, User friend, LocalDate dateCreation) {
        this.idFriend = idFriend;
        this.owner = owner;
        this.friend = friend;
        this.dateCreation = dateCreation;
    }
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

        public LocalDate getDateCreation() {
            return dateCreation;
        }

        public void setDateCreation(LocalDate dateCreation) {
            this.dateCreation = dateCreation;
        }
        //--------------------------------------------------------------------------
}
