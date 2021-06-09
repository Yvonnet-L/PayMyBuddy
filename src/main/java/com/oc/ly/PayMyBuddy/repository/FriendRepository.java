package com.oc.ly.PayMyBuddy.repository;

import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    @Query("SELECT f FROM Friend f "
            + "WHERE f.owner = :owner")
    List<Friend> FindListFriendByOwner(User owner);
}
