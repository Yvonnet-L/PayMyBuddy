package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FriendServiceTest {

    @Autowired
    IFriendService friendService;

    @Autowired
    private IUserService userService;

    public Factory factory = new Factory();

    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test findFriendByOwner")
    public void findFriendByOwnerTest(){
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        List<FriendDTO> listFriendDTO = friendService.findFriendByOwner(userDTO);
        assertTrue(listFriendDTO.size()>0);
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test addFriend")
    public void addFriendTest(){
        //GIVEN
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        List<FriendDTO> listFriendDTO = friendService.findFriendByOwner(userDTO);
        UserDTO userFriendDTO = userService.findUserById(4);
        //WHEN
        FriendDTO friendDTO = new FriendDTO(factory.constructUser(userDTO), factory.constructUser(userFriendDTO), LocalDate.now());
        friendService.addFriend(friendDTO);
        List<FriendDTO> listFriendDTOAfterAdd = friendService.findFriendByOwner(userDTO);
        //THEN
        assertEquals(listFriendDTO.size(),listFriendDTOAfterAdd.size() -1);
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test deleteFriend")
    public void deleteFriendTest(){
        //GIVEN
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        List<FriendDTO> listFriendDTO = friendService.findFriendByOwner(userDTO);
        UserDTO userFriendDTO = userService.findUserById(4);
        //WHEN
        friendService.deleteById(listFriendDTO.get(1).getIdFriend());
        List<FriendDTO> listFriendDTOAfterDelete = friendService.findFriendByOwner(userDTO);
        //THEN
        assertEquals(listFriendDTO.size(),listFriendDTOAfterDelete.size() + 1);
    }
    //--------------------------------------------------------------------------------------------------------
}
