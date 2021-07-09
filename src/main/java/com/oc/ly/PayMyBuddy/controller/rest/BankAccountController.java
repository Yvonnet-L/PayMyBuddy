package com.oc.ly.PayMyBuddy.controller.rest;

import com.oc.ly.PayMyBuddy.dto.BankAccountDTO;
import com.oc.ly.PayMyBuddy.model.BankAccount;
import com.oc.ly.PayMyBuddy.repository.BankAccountRepository;
import com.oc.ly.PayMyBuddy.service.IBankAccountService;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("management")
public class BankAccountController {

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    IBankAccountService bankAccountService;

    private static Logger logger = LogManager.getLogger(BankAccountController.class);

    public Factory factory= new Factory();

    //--------------------------------------------------------------------------------------------------------
    @GetMapping("/bankAccounts")
    public Iterable<BankAccount> getbankAccounts() {
        logger.info(" ---> Launch getbankAccounts");
        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts;
    }
    //--------------------------------------------------------------------------------------------------------
    @PostMapping(value = "/bankAccount")
    public ResponseEntity<BankAccountDTO> ajouterBank(@Validated @RequestBody BankAccountDTO bankAccountDTO) {
        logger.info(" ---> Launch ajouterBank");
        BankAccount bankAccount = factory.constructBank(bankAccountDTO);
        BankAccount newBankAccount = bankAccountRepository.save(bankAccount);
        return new ResponseEntity<BankAccountDTO>(factory.constructBankDTO(newBankAccount), HttpStatus.CREATED);
    }
    //--------------------------------------------------------------------------------------------------------
    @DeleteMapping(value= "/bankAccount")
    public ResponseEntity<BankAccountDTO> deletePerson(@RequestBody BankAccountDTO bankAccountDTO) {
        logger.info(" ---> Launch deletePerson");
        BankAccount bankAccount = factory.constructBank(bankAccountDTO);
        bankAccountRepository.delete(bankAccount);
        return new ResponseEntity<>(bankAccountDTO, HttpStatus.OK);
    }
    //--------------------------------------------------------------------------------------------------------
    @PutMapping(value= "/bankAccount")
    public ResponseEntity<BankAccountDTO> MofidierPerson(@Validated @RequestBody BankAccountDTO bankAccountDTO) {
        logger.info(" ---> Launch MofidierPerson");
        BankAccount bankAccount = factory.constructBank(bankAccountDTO);
        BankAccount bankAccountPut= bankAccountRepository.save(bankAccount);
        return new ResponseEntity<BankAccountDTO>(factory.constructBankDTO(bankAccountPut), HttpStatus.CREATED);
    }
    //--------------------------------------------------------------------------------------------------------
}
