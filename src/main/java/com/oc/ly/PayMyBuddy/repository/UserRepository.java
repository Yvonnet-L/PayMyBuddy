package com.oc.ly.PayMyBuddy.repository;

import com.oc.ly.PayMyBuddy.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    public User findUserById(Integer id);

    User findUserByEmail(String userName);

    Optional<User> findByEmail(String email);

    @Query("select u from User  u "
            + "WHERE u not in (Select friend from Friend f where f.owner= :owner)"
            + " AND u.email like :x")
    public Page<User> listUserNotFriend(User owner, @Param("x")String mc , Pageable pageable);


}
