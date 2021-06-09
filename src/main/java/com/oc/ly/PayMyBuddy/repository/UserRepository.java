package com.oc.ly.PayMyBuddy.repository;

import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    User findUserById(Integer id);

    User findUserByEmail(String userName);

    Optional<User> findByEmail(String email);
}
