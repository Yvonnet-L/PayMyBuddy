package com.oc.ly.PayMyBuddy.controller;


import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.dto.TransactionDTO;
import com.oc.ly.PayMyBuddy.dto.TransferDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.service.IFriendService;
import com.oc.ly.PayMyBuddy.service.ITransactionService;
import com.oc.ly.PayMyBuddy.service.ITransferService;
import com.oc.ly.PayMyBuddy.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
public class HomeController {

    @Autowired
    IFriendService friendService;

    @Autowired
    IUserService userService;

    @Autowired
    ITransactionService transactionService;

    @Autowired
    ITransferService transferService;

    private static Logger logger = LogManager.getLogger(HomeController.class);

    //-----------------------------------------------------------------------------------------------
    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model, @RequestParam(name="page", defaultValue = "0") int page,
                       Double amount, String friendEmail, String description,String errorMessage)
    {
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userLogDTO = userService.findUserByEmail(emailSession);
        String firstName = userLogDTO.getFirstName();
        Double wallet = userLogDTO.getWallet();
        List<FriendDTO> friends = friendService.findFriendByOwner(userLogDTO);

        Page<TransactionDTO> pageTransactions = transactionService.theLastThreeTransactions(userLogDTO, PageRequest.of(page,2));
        Page<TransferDTO> pageTransfers = transferService.theLastThreeTransfers(userLogDTO, PageRequest.of(page,2));
        Page<TransactionDTO> pageRefunds = transactionService.theLastThreeTransactionsBeneficiary(userLogDTO, PageRequest.of(page,2));

        String role = null;
        String authorisation = userLogDTO.getRoles();

        if ( authorisation.equals("ROLE_ADMIN") ) {
            role = "admin";
        }
        logger.info("--> Launch /home ");
        model.addAttribute("admin", role);
        model.addAttribute("friends", friends);
        model.addAttribute("transactions", pageTransactions);
        model.addAttribute("transfers", pageTransfers);
        model.addAttribute("refunds", pageRefunds);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("firstName", firstName);
        model.addAttribute("wallet", wallet);
        model.addAttribute("currentPage", page);
        return "home";
    }
    //-----------------------------------------------------------------------------------------------
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model)
    {
        logger.info("--> Launch /login ");
        return "login";
    }
    //-----------------------------------------------------------------------------------------------
    @RequestMapping(value = { "/save" }, method = RequestMethod.GET)
    public String save(Model model)
    {
        return "login";
    }
    //-----------------------------------------------------------------------------------------------
    @RequestMapping(value = { "/index" }, method = RequestMethod.GET)
    public String index(Model model)
    {
        return "login";
    }
    //-----------------------------------------------------------------------------------------------
    @RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        logger.info("--> Launch /logout ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}



