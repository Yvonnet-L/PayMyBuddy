package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.TransactionDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.UserRepository;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;


@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;

    public Factory factory= new Factory();

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public UserDTO findUserById(Integer id) {
        User user = userRepository.findUserById(id);
        return factory.constructUserDTO(user);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
         User user = userRepository.findUserByEmail(email);
         return factory.constructUserDTO(user);
    }


    @Override
    public Page<UserDTO> listUserNotFriend(UserDTO userDTO, String mc,Pageable pageable) {
        User user = factory.constructUser(userDTO);
        Page<User> pagesUsers = userRepository.listUserNotFriend(user,mc,pageable);
        Page<UserDTO> pagesUsersDTO= pagesUsers.map(new Function<User, UserDTO>() {
            @Override
            public UserDTO apply(User user) {
                UserDTO userDTO = new UserDTO();
                userDTO = factory.constructUserDTO(user);
                return userDTO;
            }
        });
        return pagesUsersDTO;
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
