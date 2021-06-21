package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);


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
    public Page<User> listUserNotFriend(User user, String mc,Pageable pageable) {
        return userRepository.listUserNotFriend(user,mc,pageable);
    }

    @Override
    public Boolean userExistById(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public User addUser(User user) {
        logger.info("-------> User save !");
        User userAdd = userRepository.save(user);
        return userAdd;
    }

}
