package com.oc.ly.PayMyBuddy.controller;

import com.oc.ly.PayMyBuddy.dto.FriendDTO;
import com.oc.ly.PayMyBuddy.dto.TransactionDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;
import com.oc.ly.PayMyBuddy.model.Friend;
import com.oc.ly.PayMyBuddy.model.Transaction;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.service.IFriendService;
import com.oc.ly.PayMyBuddy.service.ITransactionService;
import com.oc.ly.PayMyBuddy.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransactionController {


    private static Logger logger = LogManager.getLogger(TransactionController.class);

    @Autowired
    IFriendService friendService;

    @Autowired
    ITransactionService transactionService;

    @Autowired
    IUserService userService;

    @GetMapping("/deleteTransaction")
    public String delete(Integer id, int page){
        transactionService.deleteById(id);
        return"redirect:/transaction?page="+page;
    }


    @RequestMapping(value = { "/transaction" }, method = RequestMethod.GET)
    public String home(Model model,
                        @RequestParam(name="page", defaultValue = "0") int page,
                        @RequestParam(name="errorMessage", defaultValue = "") String errorMessage,
                        @RequestParam(name="amount", defaultValue = "") Double amount,
                        @RequestParam(name="friendEmail", defaultValue = "") String friendEmail,
                        @RequestParam(name="description", defaultValue = "") String description )
    {


            String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();

            User userLog = userService.findUserByEmail(emailSession);

            List<FriendDTO> friends = friendService.findFriendByOwner(userLog);

            Page<Transaction> pageTransactions = transactionService.findAllByPayer(userLog, PageRequest.of(page, 4));

            String role = null;
            String authorisation = userLog.getRoles();

            if (authorisation.equals("ROLE_ADMIN")) {
                role = "admin";
            }

            model.addAttribute("admin", role);
            model.addAttribute("friends", friends);
            model.addAttribute("transactions", pageTransactions.getContent());
            model.addAttribute("pages", new int[pageTransactions.getTotalPages()]);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("currentPage", page);
            model.addAttribute("description", description);
            model.addAttribute("friend", friendEmail);
            model.addAttribute("amount", amount);

            return "transaction";


    }



    @PostMapping(value = { "/transaction" })
    public String addTransaction(Model model, @RequestParam(name="page", defaultValue = "0") int page, Double amount,
                                 String friendEmail, String description, String errorMessage)
    {
        logger.info(" ---> Launch of the request:  Post /home ");
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();

        User userLog = new User();
        try {
            userLog = userService.findUserByEmail(emailSession);
            }catch (Exception e){
            throw new DataNotFoundException("Problem with the database");
            }

        if (friendEmail.equals("")) {
            errorMessage="You must choose an email! ";
        }else {
            User beneficiary = userService.findUserByEmail(friendEmail);
            User payer = userLog;
            logger.info("  ---> Data of Payer & Beneficiary found");
            try {
                TransactionDTO newTransactionDTO = new TransactionDTO(payer, beneficiary, amount, description);
                transactionService.addTransaction(newTransactionDTO);
            }
            catch (DataNotFoundException | DataNotConformException e){
                errorMessage = e.getMessage();
                return"redirect:/transaction?page="+page+
                                "&errorMessage="+errorMessage+
                                "&friendEmail="+friendEmail+
                                "&amount="+amount+
                                "&description="+description;
            }
            errorMessage = "Transaction sauvegardée";
        }
            return"redirect:/transaction?page="+page+
                    "&errorMessage="+errorMessage+
                    "&friendEmail="+friendEmail+
                    "&amount="+amount+
                    "&description="+description;

    }

}
