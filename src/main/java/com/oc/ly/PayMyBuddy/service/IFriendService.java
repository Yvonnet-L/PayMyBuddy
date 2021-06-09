package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.User;

import java.util.List;


public interface IFriendService {

    public Friend addFriend(FriendDTO friendDTO);

    public Friend updateFriend(Friend friend);

    public Friend deleteFriend(Friend friend);

    public List<Friend> allFriends();

    public List<Friend> findFriendByOwner(User owner);


}

