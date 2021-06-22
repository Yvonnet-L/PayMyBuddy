package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IUserService {

    public User findByUserName(String userName);

    public User findUserById(Integer id);

    public UserDTO findUserByEmail(String email);

  //  public Optional<User> findByEmail(String email);

   // public Page<User> ListUserNotFriend(User user, String mc,Pageable pageable);

    public User addUser(User user);

    public Page<UserDTO> listUserNotFriend(UserDTO owner, @Param("x")String mc , Pageable pageable);

    public Boolean userExistById ( Integer id );


}
