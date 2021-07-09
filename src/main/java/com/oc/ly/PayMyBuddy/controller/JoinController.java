package com.oc.ly.PayMyBuddy.controller;

import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataAlreadyExistException;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class JoinController {

    @Autowired
    IUserService userService;

    private static Logger logger = LogManager.getLogger(JoinController.class);

    //-----------------------------------------------------------------------------------------------
    @RequestMapping(value = { "/join" }, method = RequestMethod.GET)
    public String join(Model model,  String password, String confirmation,
                       String firstName, String lastName, String email, String errorMessage)
    {
        logger.info("--> Launch /join ");
        model.addAttribute("confirmation", confirmation);
        model.addAttribute("password", password);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("email", email);
        model.addAttribute("errorMessage", errorMessage);
        return "join";
    }
    //-----------------------------------------------------------------------------------------------
    @PostMapping(value = { "/join" })
    public String addNewUser(Model model,  String password, String confirmation,
                                 String firstName, String lastName, String email, String errorMessage)
    {
        logger.info("--> Launch POST /join ");

            try {
                UserDTO newUserDTO = new UserDTO(lastName,firstName, password, email);
                userService.saveNewUser(newUserDTO, confirmation);
                return"redirect:/login";
            }
            catch (DataNotConformException | DataAlreadyExistException e){
                errorMessage = e.getMessage();
                return "redirect:/join?" +
                        "&firstName="+firstName+
                        "&lastName="+lastName+
                        "&email="+email+
                        "&password="+password+
                        "&confirmation="+confirmation+
                        "&errorMessage="+errorMessage;
            }

    }
    //-----------------------------------------------------------------------------------------------
}
