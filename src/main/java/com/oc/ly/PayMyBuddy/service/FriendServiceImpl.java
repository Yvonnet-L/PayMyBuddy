package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements IFriendService{

    @Autowired
    FriendRepository friendRepository;

    @Override
    public Friend addFriend(Friend friend) {
        return null;
    }

    @Override
    public Friend updateFriend(Friend friend) {
        return null;
    }

    @Override
    public Friend deleteFriend(Friend friend) {
        return null;
    }

    @Override
    public List<Friend> allFriends() {
        return null;
    }

    @Override
    public List<Friend> findFriendByOwner(User owner) {

       // User userOwner = new User();

        List<Friend> friendsList = friendRepository.FindListFriendByOwner(owner);
        if ( friendsList!=null ) {
            return friendsList;
        }else {
            return null;
        }

    }

}
