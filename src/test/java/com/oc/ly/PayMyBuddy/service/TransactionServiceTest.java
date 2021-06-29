package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.constants.TransferType;
import com.oc.ly.PayMyBuddy.dto.TransactionDTO;
import com.oc.ly.PayMyBuddy.dto.TransferDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    ITransactionService transactionService;

    @Autowired
    IUserService userService;

    public Factory factory  = new Factory();

    List<UserDTO> listUserDTO = new ArrayList<UserDTO>();

    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test deleteById")
    public void deleteByIdTest(){
        //GIVEN
        List<TransactionDTO> transactionDTOList = transactionService.findAll();
        Integer sizeBeforeDelete = transactionDTOList.size();
        //WHEN
        transactionService.deleteById(1);
        transactionDTOList = transactionService.findAll();
        Integer sizeAfterDelete = transactionDTOList.size();
        //THEN
        assertEquals(sizeBeforeDelete, sizeAfterDelete + 1);
    }

    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test addTransaction")
    public void addTransactionTest(){
        //GIVEN
        listUserDTO = userService.findAll();
        User userPayer = factory.constructUser(listUserDTO.get(1));
        User userBeneficiary = factory.constructUser(listUserDTO.get(2));
        Double wallePayertBefore = userPayer.getWallet();
        Double walleBeneficiarytBefore = userBeneficiary.getWallet();
        TransactionDTO transactionDTO = new TransactionDTO( userPayer, userBeneficiary, 15.0,"cinéma");
        //WHEN
        transactionDTO = transactionService.addTransaction(transactionDTO);
        Double wallePayertAfter = userPayer.getWallet();
        Double walleBeneficiarytAfter = userBeneficiary.getWallet();
        //THEN
        assertTrue(  wallePayertAfter <  wallePayertBefore );
        assertTrue(  walleBeneficiarytAfter ==  walleBeneficiarytBefore + 15 );
    }

}
