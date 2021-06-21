package com.oc.ly.PayMyBuddy.repository;

import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    @Query("SELECT f FROM Friend f "
            + "WHERE f.owner = :owner")
    public List<Friend> findListFriendByOwner(User owner);

    @Query("SELECT f FROM Friend f "
            + "WHERE f.owner = :owner")
    public Page<Friend> findFriendByOwner(User owner, Pageable pageable);

    @Query("SELECT f FROM Friend f "
            + "WHERE f.owner = :owner and f.friend.email like :x")
    public Page<Friend> ChercherFriendByOwner(User owner, @Param("x")String mc , Pageable pageable);


}
