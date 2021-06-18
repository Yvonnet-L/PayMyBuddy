package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IFriendService {

    public Friend addFriend(Friend friendDTO);

    public Friend updateFriend(Friend friend);

    public Friend deleteFriend(Friend friend);

    public List<Friend> allFriends();

    public List<FriendDTO> findFriendByOwner(User owner);

    public Page<Friend> findFriendByOwner(User owner, Pageable pageable);

    public Friend deleteById(Integer id);

}

