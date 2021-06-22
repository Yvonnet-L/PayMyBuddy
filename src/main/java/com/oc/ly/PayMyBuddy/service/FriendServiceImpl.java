package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.FriendRepository;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements IFriendService{

    private static Logger logger = LogManager.getLogger(FriendServiceImpl.class);

    @Autowired
    FriendRepository friendRepository;

    public Factory factory = new Factory();

    @Override
    public Friend addFriend(FriendDTO friendDTO) {
        Friend friend = factory.constructFriend(friendDTO);
        friendRepository.save(friend);
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
    public List<FriendDTO> findFriendByOwner(UserDTO ownerDTO) {

        List<FriendDTO> friendsDTOList= new ArrayList<>();
        User owner = factory.constructUser(ownerDTO);
        List<Friend> friendsList = friendRepository.findListFriendByOwner(owner);

        if ( friendsList!=null ) {
            for ( Friend f : friendsList ) {
                FriendDTO friendDTO = new FriendDTO();
                friendDTO = factory.constructFriendDTO( f );
                friendsDTOList.add(friendDTO);
            }
            return friendsDTOList;
        }else {
            return null;
        }

    }

    @Override
    public Page<Friend> findFriendByOwner(User owner, Pageable pageable) {
        Page<Friend> pagesFriends= friendRepository.findFriendByOwner(owner, pageable);
        return pagesFriends;
    }

    @Override
    public Friend deleteById(Integer id) {
        friendRepository.deleteById(id);
        return null;
    }

}
