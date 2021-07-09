package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.BankAccountDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.BankAccount;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.BankAccountRepository;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountServiceImpl implements IBankAccountService{

    private static Logger logger = LogManager.getLogger(BankAccountServiceImpl.class);

    @Autowired
    BankAccountRepository bankAccountRepository;

    public Factory factory = new Factory();

    //-----------------------------------------------------------------------------------------------
    @Override
    public List<BankAccountDTO> findBankAccountByUser(UserDTO userDTO) {
        logger.info(" ---> Launch of the search for a user's account");
        User user = factory.constructUser(userDTO);
        List<BankAccount> listBankAccount = bankAccountRepository.findBankAccountByUser(user);
        List<BankAccountDTO> listBankAccountDTO = new ArrayList<>();
        for (BankAccount bankAccount: listBankAccount) {
            BankAccountDTO bankAccountDTO = factory.constructBankDTO(bankAccount);
            listBankAccountDTO.add(bankAccountDTO);
        }
        return listBankAccountDTO  ;
    }
    //-----------------------------------------------------------------------------------------------
    @Override
    public BankAccountDTO addAccount(String rib, UserDTO userDTO ) {
        logger.info(" ---> Launch of account creation");
        User user = factory.constructUser(userDTO);
        BankAccount newBankAccount = new BankAccount(user,rib);
        BankAccount bankAccountAdd = bankAccountRepository.save(newBankAccount);
        BankAccountDTO bankAccountDTO = factory.constructBankDTO(bankAccountAdd);
        logger.info(" -----> BankAccount created!");
        return bankAccountDTO;
    }
    //-----------------------------------------------------------------------------------------------
    @Override
    public void deleteAccount(Integer id) {
        logger.info(" ---> Launch of account deletion ");
        bankAccountRepository.deleteById(id);
        logger.info(" -----> BankAccount deleted!");
    }
    //-----------------------------------------------------------------------------------------------
}
