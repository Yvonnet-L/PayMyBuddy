package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IFriendService {

    public FriendDTO addFriend(FriendDTO friendDTO);

    public List<FriendDTO> findFriendByOwner(UserDTO owner);

    public FriendDTO deleteById(Integer id);

}

