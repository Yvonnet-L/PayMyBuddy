package com.oc.ly.PayMyBuddy.controller;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.service.IFriendService;
import com.oc.ly.PayMyBuddy.service.IUserService;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ContactController {

    private static Logger logger = LogManager.getLogger(ContactController.class);

    @Autowired
    IFriendService friendService;

    @Autowired
    IUserService userService;

    public Factory factory = new Factory();


    @GetMapping("/deleteContact")
    public String delete(Integer id){
        friendService.deleteById(id);
        return"redirect:/contact";
    }


    @GetMapping("/addContact")
    public String add(Integer idFriend){
        //-- Security Context - récupération du userLog
        logger.info(" --->   idFriend: " + idFriend );
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userLog = userService.findUserByEmail(emailSession);
        UserDTO uFriend = userService.findUserById(idFriend);
        logger.info(" --->  ");
        logger.info("userOwner: " + userLog.getEmail() +" userFrriend: " + uFriend.getEmail()   );
        LocalDate date = LocalDate.now();
        User owner = factory.constructUser(userLog);
        FriendDTO newFriendDTO = new FriendDTO(owner, factory.constructUser(uFriend), date);
        friendService.addFriend(newFriendDTO);
        return"redirect:/contact";
    }

    @RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
    public String home(Model model,
                       @RequestParam(name="page", defaultValue = "0") int page,
                       @RequestParam(name="motCle", defaultValue = "") String mc,
                       @RequestParam(name="errorMessage", defaultValue = "") String errorMessage)
    {
        //-- Security Context - récupération du userLog
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userLog = userService.findUserByEmail(emailSession);
        //-- Récupération de la liste des amis
       // List<Friend> friends = friendService.findFriendByOwner(userLog);
       // Page<Friend> pageFriends = friendService.findFriendByOwner(userLog, PageRequest.of(page, 3));
        List<FriendDTO> pageFriends = friendService.findFriendByOwner(userLog);
       // Page<Friend> pageFriends2 = friendService.findFriendByOwner(userLog, PageRequest.of(page, 2));
        Page<UserDTO> pageUsersNotFriend = userService.listUserNotFriend(userLog,"%"+mc+"%",PageRequest.of(page, 3));

        String role = null;
        String authorisation = userLog.getRoles();

        if (authorisation.equals("ROLE_ADMIN")) {
            role = "admin";
        }

        model.addAttribute("admin", role);
        model.addAttribute("friends", pageFriends);
        model.addAttribute("notFriends", pageUsersNotFriend .getContent());
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", new int[pageUsersNotFriend .getTotalPages()]);
        model.addAttribute("motCle", mc);

        return "contact";
    }

}
