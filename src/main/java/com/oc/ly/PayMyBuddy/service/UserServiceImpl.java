package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;


    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public User findUserById(Integer id) {
        User user = userRepository.findUserById(id);
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Page<User> ListUserNotFriend(User user, String mc,Pageable pageable) {
        return userRepository.ListUserNotFriend(user,mc,pageable);
    }


    /*
    public Boolean findUserByEmail(String email) {
        if (true) {
            return true;
        } else throw new DataNotFoundException("---> Update avorté, adresse ou station non conforne");
    }

     */
}
