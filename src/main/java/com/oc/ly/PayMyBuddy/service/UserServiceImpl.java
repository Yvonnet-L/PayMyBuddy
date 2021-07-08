package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataAlreadyExistException;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.UserRepository;
import com.oc.ly.PayMyBuddy.tool.Factory;
import com.oc.ly.PayMyBuddy.tool.StringUtilsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Factory factory = new Factory();

    StringUtilsService stringUtilsService = new StringUtilsService();

    private static Logger logger = LogManager.getLogger(UserServiceImpl.class);


    @Override
    public List<UserDTO> findAll() {
        logger.info(" ---> Launch findAll");
        List<User> listUser = userRepository.findAll();
        List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
        for (User user: listUser) {
            logger.info(user.getId());
            listUserDTO.add(factory.constructUserDTO(user));
        }
        return listUserDTO;
    }

    @Override
    public UserDTO findUserById(Integer id) {
        logger.info(" ---> Launch findUserById");
        User user = userRepository.findUserById(id);
        if (user == null) {
            logger.info(" *** User not found ***");
            throw new DataNotFoundException("Problem request: findUserById --> user not found");
        }
        return factory.constructUserDTO(user);
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        logger.info(" ---> Launch finUserByEmail");
         User user = userRepository.findUserByEmail(email);
         return factory.constructUserDTO(user);
    }


    @Override
    public Page<UserDTO> listUserNotFriend(UserDTO userDTO, String mc,Pageable pageable) {
        logger.info(" ---> Launch listUserNotFriend");
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
        logger.info(" ---> Launch userExistById");
        return userRepository.existsById(id);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        logger.info(" ---> Launch saveUser");
        User userAdd = new User();
            userAdd = userRepository.save(factory.constructUser(userDTO));
            return factory.constructUserDTO(userAdd);


    }

    @Override
    public UserDTO saveNewUser(UserDTO userDTO) {
        logger.info(" ---> Launch saveNewUser");
        User userAdd = new User();
        if(stringUtilsService.checkStringEmail(userDTO.getEmail())==false){
            throw new DataNotConformException("it is not a email!");
        }
        if (userRepository.findUserByEmail(userDTO.getEmail())==null){
            userDTO.setRoles("ROLE_USER");
            userDTO.setWallet(0.0);
            userDTO.setActive(true);
            userDTO.setCreationDate(LocalDate.now());
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            logger.info(userDTO.getPassword());
            userAdd = userRepository.save(factory.constructUser(userDTO));
            return factory.constructUserDTO(userAdd);
        }else{
            throw new DataAlreadyExistException("This email already exist !");
        }

    }

}
