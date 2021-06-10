package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{
    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public User findUserById(Integer id) {
        return null;
    }

    @Override
    public User findUserByEmail(String userName) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
