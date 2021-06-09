package com.oc.ly.PayMyBuddy.controller;


import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.MyUserDetails;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.FriendRepository;
import com.oc.ly.PayMyBuddy.repository.TransactionRepository;
import com.oc.ly.PayMyBuddy.repository.UserRepository;
import com.oc.ly.PayMyBuddy.security.MyUserDetailService;
import com.oc.ly.PayMyBuddy.service.IFriendService;
import com.sun.xml.bind.util.AttributesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Controller
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    IFriendService friendService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model)
    {
        return "login";
    }

    @RequestMapping(value = { "/login1" }, method = RequestMethod.GET)
    public String login1(Model model)
    {
        return "login1";
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
    public String home(Model model, @RequestParam(name="page", defaultValue = "0") int page) {

       User user = userRepository.findByUserName("Lolo2");
       List<Friend> friends = friendService.findFriendByOwner(user);

      // List<Friend> friends = friendRepository.findAll();
        System.out.println( "combien d'amis trouvés :" + friends.size());
        Page<User> pageUsers = userRepository.findAll(PageRequest.of(page,3));
        String role = null;
        String userNameLog = SecurityContextHolder.getContext().getAuthentication().getName();
        String authorisation = userRepository.findByUserName(userNameLog).getRoles();

        if ( authorisation.equals("ROLE_ADMIN") ) {
            role = "admin";
        }
        model.addAttribute("admin", role);
        model.addAttribute("friends", friends);
        model.addAttribute("users", pageUsers.getContent());
        model.addAttribute("pages", new int[pageUsers.getTotalPages()]);
        model.addAttribute("currentPage", page);
        return  "home";
    }

    @GetMapping("/delete")
    public String delete(Integer id, int page){
            //userRepository.deleteById(id);
            transactionRepository.deleteById(id);
            return"redirect:/home2?page="+page;
    }


    @RequestMapping(value = { "/home2" }, method = RequestMethod.GET)
    public String home2(Model model, @RequestParam(name="page", defaultValue = "0") int page,
                                Double amount, String friendEmail, String description, String errorMessage)
    {
        String userNameLog = SecurityContextHolder.getContext().getAuthentication().getName();
        User userLog = userRepository.findByUserName(userNameLog);

        List<Friend> friends = friendService.findFriendByOwner(userLog);
       // Page<Transaction> pageTransactions = transactionRepository.findAll(PageRequest.of(0,3));

        Page<Transaction> pageTransactions = transactionRepository.findAllByPayer(userLog,PageRequest.of(page,3));

        String role = null;
        String authorisation = userLog.getRoles();

        if ( authorisation.equals("ROLE_ADMIN") ) {
            role = "admin";
        }

        model.addAttribute("admin", role);
        model.addAttribute("friends", friends);
        model.addAttribute("transactions", pageTransactions.getContent());
        model.addAttribute("pages", new int[pageTransactions.getTotalPages()]);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("currentPage", page);
        return "home2";
    }

    @PostMapping(value = { "/home2" })
    public String addTransaction(Model model, Double amount, String friendEmail, String description, String errorMessage)
    {
        LocalDate createDate = LocalDate.now();
        String userNameLog = SecurityContextHolder.getContext().getAuthentication().getName();
        User userLog = userRepository.findByUserName(userNameLog);

        if (friendEmail.equals("vide")) {
            errorMessage="Vous devez choisir un email! ";
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("description", description);
            model.addAttribute("amount", amount);

        }else {
            logger.info("---> On entre bien dans le Post /home2 ");
            logger.info("---> valeur des variables:  montant: " + amount + " , email: " + friendEmail + " , description " + description);
            User beneficiary = userRepository.findUserByEmail(friendEmail);
            User payer = userRepository.findUserById(userLog.getId());
            logger.info("---> Pas d'erreur sur les recherches repo");
            Transaction newTransaction = new Transaction(payer, beneficiary, createDate, amount, description);
            logger.info("---> création de la nouvelle transaction ");
            transactionRepository.save(newTransaction);
            logger.info("---> Sauvegarde transaction ok !");
        }
        return "redirect:/home2";

    }




}



