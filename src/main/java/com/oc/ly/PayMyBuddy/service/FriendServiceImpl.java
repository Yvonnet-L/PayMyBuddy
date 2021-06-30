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
    public FriendDTO addFriend(FriendDTO friendDTO) {
        Friend friend = factory.constructFriend(friendDTO);
        logger.info("--- friendDTO ID:- "+ friendDTO.getIdFriend()  );
        logger.info("--- friend ID:- "+ friend.getIdFriend()  );
        friendDTO = factory.constructFriendDTO(friendRepository.save(friend));

        return friendDTO;
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
    public FriendDTO deleteById(Integer id) {
        logger.info(" --->   idFriend: " + id);
        friendRepository.deleteById(id);
        return null;
    }

}
