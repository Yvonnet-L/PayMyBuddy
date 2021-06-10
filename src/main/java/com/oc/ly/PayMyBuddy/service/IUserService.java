package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.model.User;

import java.util.Optional;

public interface IUserService {

    public User findByUserName(String userName);

    public User findUserById(Integer id);

    public User findUserByEmail(String userName);

    public Optional<User> findByEmail(String email);
}
