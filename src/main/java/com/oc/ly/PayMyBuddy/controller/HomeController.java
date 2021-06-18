package com.oc.ly.PayMyBuddy.controller;


import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.Transfer;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.FriendRepository;
import com.oc.ly.PayMyBuddy.repository.TransactionRepository;
import com.oc.ly.PayMyBuddy.repository.TransferRepository;
import com.oc.ly.PayMyBuddy.repository.UserRepository;
import com.oc.ly.PayMyBuddy.service.IFriendService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;


@Controller
public class HomeController {


    private static Logger logger = LogManager.getLogger(HomeController.class);

    @Autowired
    IFriendService friendService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransferRepository transferRepository;


    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model)
    {
        return "login";
    }

    @RequestMapping(value = { "/save" }, method = RequestMethod.GET)
    public String save(Model model)
    {
        return "login";
    }

    @RequestMapping(value = { "/index" }, method = RequestMethod.GET)
    public String index(Model model)
    {
        return "login";
    }

    @RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
    public String admin() { return  "admin";
    }





    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model, @RequestParam(name="page", defaultValue = "0") int page,
                                Double amount, String friendEmail, String description,String errorMessage)
    {
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();

        User userLog = userRepository.findUserByEmail(emailSession);
        String firstName = userLog.getFirstName();
        Double wallet = userLog.getWallet();
        List<FriendDTO> friends = friendService.findFriendByOwner(userLog);

        logger.info("---> On entre bien dans /home ");
        Page<Transaction> pageTransactions = transactionRepository.theLastThreeTransactions(userLog, PageRequest.of(page,2));
        Page<Transfer> pageTransfers = transferRepository.theLastThreeTransfers(userLog, PageRequest.of(page,2));
        Page<Transaction> pageRefunds = transactionRepository.theLastThreeTransactionsBeneficiary(userLog, PageRequest.of(page,2));
        logger.info("---> On passe bien les requete " + pageTransfers.getSize()) ;

        String role = null;
        String authorisation = userLog.getRoles();

        if ( authorisation.equals("ROLE_ADMIN") ) {
            role = "admin";
        }

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






}



