package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.constants.TransferType;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    IUserService userService;

    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test findUserByEmail")
    public void findUserByEmailTest(){
        //GIVEN
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        //WHEN
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        //THEN
        assertEquals(userDTO.getEmail(), "lolo@email.com");
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test findUserById")
    public void findUserByIdTest(){
        //GIVEN
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        //WHEN
        UserDTO userFind = userService.findUserById(userDTO.getId());
        //THEN
        assertEquals(userDTO.getEmail(), userFind.getEmail());
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test findUserByEmail")
    public void listUserNotFriendTest(){
        //GIVEN
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        int page = 0;
        //WHEN
        Page<UserDTO> pagesTransferDTO = userService.listUserNotFriend(userDTO,"", PageRequest.of(page,2)) ;
        //THEN
        assertEquals(pagesTransferDTO.getTotalPages(),0);
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test userExistById")
    public void userExistByIdTest(){
        //GIVEN
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        //WHEN
        Boolean exist = userService.userExistById(userDTO.getId());
        //THEN
        assertEquals(exist,true);
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test saveUser")
    public void saveUserTest(){
        //GIVEN
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        userDTO.setEmail("newUser@email.com");
        userDTO.setId(0);
        //WHEN
        UserDTO newUserDTO = userService.saveUser(userDTO);
        //THEN
        assertTrue(newUserDTO.getId()>0);
    }
    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test saveNewUser")
    public void saveNewUserTest(){
        //GIVEN
        UserDTO userDTO = new UserDTO("Do","Nathy","0123456789","nathy@email.com");
        //WHEN
        UserDTO newUserDTO = userService.saveNewUser(userDTO,"0123456789");
        //THEN
        assertTrue(newUserDTO.getId()>0);
    }
    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test saveNewUser with bad email")
    public void saveNewUserWithBadEmailTest(){
        //GIVEN
        UserDTO userDTO = new UserDTO("Do","Nathy","0123456789","badEmail.com");
        //WHEN
        //THEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO, "0123456789"));

    }
    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test saveNewUser with bad name")
    public void saveNewUserWithBadNameTest(){
        //GIVEN
        UserDTO userDTO = new UserDTO("Do?=!oo","Nathy","0123456789","nathy@email.com");
        //WHEN
        //THEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO,"0123456789"));

    }
    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test saveNewUser with bad confirmationPass")
    public void saveNewUserWithBadConfirmationPassTest(){
        //GIVEN
        UserDTO userDTO = new UserDTO("Do?=!oo","Nathy","0123456789","nathy@email.com");
        //WHEN
        //THEN
        assertThrows(DataNotConformException.class, () -> userService.saveNewUser(userDTO,"badConfirmation"));

    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test saveUser")
    public void findAllUserTest(){
        //GIVEN
        //WHEN
        List<UserDTO> listUserDTO = userService.findAll();
        //THEN
        assertTrue(listUserDTO.size()>=4);
    }
    //--------------------------------------------------------------------------------------------------------
}
