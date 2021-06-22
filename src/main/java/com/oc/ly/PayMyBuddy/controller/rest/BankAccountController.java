package com.oc.ly.PayMyBuddy.controller.rest;

import com.oc.ly.PayMyBuddy.dto.BankAccountDTO;
import com.oc.ly.PayMyBuddy.model.BankAccount;
import com.oc.ly.PayMyBuddy.repository.BankAccountRepository;
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

    //--------------------------------------------------------------------------------------------------------
    @GetMapping("/bankAccounts")
    public Iterable<BankAccount> getbankAccounts() {
        Iterable<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts;
    }
    //--------------------------------------------------------------------------------------------------------
    @PostMapping(value = "/bankAccount")
    public ResponseEntity<BankAccount> ajouterBank(@Validated @RequestBody BankAccount bankAccount) {
        BankAccount newBankAccount = bankAccountRepository.save(bankAccount);
        return new ResponseEntity<BankAccount>(newBankAccount, HttpStatus.CREATED);
    }
    //--------------------------------------------------------------------------------------------------------
    @DeleteMapping(value= "/bankAccount")
    public ResponseEntity<BankAccount> deletePerson(@RequestBody BankAccount bankAccount) {

        bankAccountRepository.delete(bankAccount);
        return new ResponseEntity<>(bankAccount, HttpStatus.OK);
    }
    //--------------------------------------------------------------------------------------------------------
    @PutMapping(value= "/bankAccount")
    public ResponseEntity<BankAccount> MofidierPerson(@Validated @RequestBody BankAccount bankAccount) {

        BankAccount bankAccountPut= bankAccountRepository.save(bankAccount);
        return new ResponseEntity<>(bankAccountPut, HttpStatus.CREATED);
    }
    //--------------------------------------------------------------------------------------------------------
}
