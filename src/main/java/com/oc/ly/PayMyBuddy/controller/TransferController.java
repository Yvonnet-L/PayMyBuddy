package com.oc.ly.PayMyBuddy.controller;


import com.oc.ly.PayMyBuddy.dto.BankAccountDTO;
import com.oc.ly.PayMyBuddy.dto.TransferDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;
import com.oc.ly.PayMyBuddy.model.BankAccount;
import com.oc.ly.PayMyBuddy.model.Transfer;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.service.IBankAccountService;
import com.oc.ly.PayMyBuddy.service.IFriendService;
import com.oc.ly.PayMyBuddy.service.ITransferService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TransferController {

    private static Logger logger = LogManager.getLogger(TransferController.class);

    @Autowired
    IFriendService friendService;

    @Autowired
    IUserService userService;

    @Autowired
    IBankAccountService bankAccountService;

    @Autowired
    ITransferService transferService;

    public Factory factory = new Factory();

    @RequestMapping(value = { "/transfer" }, method = RequestMethod.GET)
    public String transfer(Model model,
                       @RequestParam(name="page", defaultValue = "0") int page,
                       @RequestParam(name="motCle", defaultValue = "") String mc,
                       @RequestParam(name="errorMessage", defaultValue = "") String errorMessage)
    {
        //-- Security Context - récupération du userLog
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userLog = userService.findUserByEmail(emailSession);
        //-- Récupération de la liste des rib

        List<BankAccountDTO> bankAccounts = bankAccountService.findBankAccountByUser(userLog);

        Page<TransferDTO> pageTransfers = transferService.findAllByUser(userLog, PageRequest.of(page,2));

        String role = null;
        String authorisation = userLog.getRoles();

        if (authorisation.equals("ROLE_ADMIN")) {
            role = "admin";
        }

        model.addAttribute("admin", role);
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("currentPage", page);
        model.addAttribute("bankAccounts", bankAccounts);
        model.addAttribute("transfers", pageTransfers);
        model.addAttribute("pages", new int[pageTransfers .getTotalPages()]);

        return "transfer";
    }


   // ----  /deleteAccount  ----------------

    @GetMapping("/deleteAccount")
    public String delete(Integer id){
        bankAccountService.deleteAccount(id);
        return"redirect:/transfer";
    }

    // ----- /addAccount   ----------------

    @PostMapping(value = { "/addBankAccount" })
    public String addAccount(Model model, @RequestParam(name="page", defaultValue = "0") int page, String rib){

        logger.info(" ---> entrée dans /addBankAccount " + rib);
        //-- Security Context - récupération du userLog
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userLog = userService.findUserByEmail(emailSession);
        bankAccountService.addAccount( rib ,userLog);
        return"redirect:/transfer";
    }

    @PostMapping(value = { "/addTransfer" })
    public String addTransfer(Model model, @RequestParam(name="page", defaultValue = "0") int page,
                              String rib, double amount, String type, String errorMessage){

        logger.info(" ---> entrée dans /addBTransfer " + rib + " - " + amount + " - " + type);
        //-- Security Context - récupération du userLog
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userLog = userService.findUserByEmail(emailSession);
        User user = factory.constructUser(userLog);
        try{
            transferService.addTransfer(rib,type, amount, user);
        }
        catch (DataNotFoundException | DataNotConformException e){
            errorMessage = e.getMessage();
            return"redirect:/transfer?page="+page+
                    "&errorMessage="+errorMessage;
        }
        errorMessage = "Transfer saved";

        return"redirect:/transfer?page="+page+
                "&errorMessage="+errorMessage;
    }
}
