package com.oc.ly.PayMyBuddy.controller;


import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

    @Autowired
    IUserService userService;

    private static Logger logger = LogManager.getLogger(ProfileController.class);

    //-----------------------------------------------------------------------------------------------
    @RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
    public String login(Model model, String errorMessage, String firstName, String lastName, String email,
                        String password, String confirmation)
    {
        logger.info("--> Launch /profile ");
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userLogDTO = userService.findUserByEmail(emailSession);
        firstName = userLogDTO.getFirstName();
        lastName = userLogDTO.getUserName();
        email = userLogDTO.getEmail();
        password = userLogDTO.getPassword();
        confirmation = password;
        String role = null;
        String authorisation = userLogDTO.getRoles();
        if ( authorisation.equals("ROLE_ADMIN") ) {
            role = "admin";
        }
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        model.addAttribute("confirmation", confirmation);
        model.addAttribute("admin", role);
        model.addAttribute("errorMessage", errorMessage);


        return "profile";
    }
    //-----------------------------------------------------------------------------------------------

}
