package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.model.BankAccount;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.BankAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements IBankAccountService{

    private static Logger logger = LogManager.getLogger(BankAccountServiceImpl.class);

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccount> findBankAccountByUser(User user) {
        logger.info(" ---> Launch of the search for a user's account");
        List<BankAccount> ListResult = bankAccountRepository.findBankAccountByUser(user);
        return ListResult;
    }

    @Override
    public void addAccount(String rib, User user ) {
        logger.info(" ---> Launch of account creation");
        BankAccount newBankAccount = new BankAccount(user,rib);
        bankAccountRepository.save(newBankAccount);
        logger.info(" -----> BankAccount created!");
    }

    @Override
    public void deleteAccount(Integer id) {
        logger.info(" ---> Launch of account deletion ");
        bankAccountRepository.deleteById(id);
        logger.info(" -----> BankAccount deleted!");
    }
}
