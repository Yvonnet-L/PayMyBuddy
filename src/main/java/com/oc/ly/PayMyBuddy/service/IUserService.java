package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    public List<UserDTO> findAll();

    public UserDTO findUserById(Integer id);

    public UserDTO findUserByEmail(String email);

    public UserDTO saveUser(UserDTO userDTO);

    public Page<UserDTO> listUserNotFriend(UserDTO owner, @Param("x")String mc , Pageable pageable);

    public Boolean userExistById ( Integer id );


}
