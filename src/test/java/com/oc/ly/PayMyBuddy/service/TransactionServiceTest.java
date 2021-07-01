package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.constants.TransferType;
import com.oc.ly.PayMyBuddy.dto.TransactionDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.exceptions.DataNotConformException;
import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;
import com.oc.ly.PayMyBuddy.exceptions.WalletNotEnoughException;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    ITransactionService transactionService;

    @Autowired
    IUserService userService;

    public Factory factory  = new Factory();

    List<UserDTO> listUserDTO = new ArrayList<UserDTO>();
    //-----------------------------------------------------------------------------------------------------
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
    //-----------------------------------------------------------------------------------------------------
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
    //-----------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test addTransactionNegativeTest")
    public void addTransactionNegativeTest(){
        //GIVEN
        listUserDTO = userService.findAll();
        User userPayer = factory.constructUser(listUserDTO.get(1));
        User userBeneficiary = factory.constructUser(listUserDTO.get(2));
        Double wallePayertBefore = userPayer.getWallet();
        Double walleBeneficiarytBefore = userBeneficiary.getWallet();
        TransactionDTO transactionDTO = new TransactionDTO( userPayer, userBeneficiary, -15.0,"cinéma");
        //WHEN
        //THEN
        assertThrows(WalletNotEnoughException.class, () ->  transactionService.addTransaction(transactionDTO));
        Double wallePayertAfter = userPayer.getWallet();
        Double walleBeneficiarytAfter = userBeneficiary.getWallet();
        assertTrue(  wallePayertAfter ==  wallePayertBefore );
        assertTrue(  walleBeneficiarytAfter ==  walleBeneficiarytBefore );
    }
    //-----------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test addTransactionOneUserNotFoundTest")
    public void addTransactionOneUserNotFoundTest() {
        //GIVEN
        listUserDTO = userService.findAll();
        User userPayer = factory.constructUser(listUserDTO.get(1));
        User userBeneficiary = factory.constructUser(listUserDTO.get(2));
        Double wallePayertBefore = userPayer.getWallet();
        Double walleBeneficiarytBefore = userBeneficiary.getWallet();
        //WHEN
        userPayer.setId(0);
        TransactionDTO transactionDTO = new TransactionDTO(userPayer, userBeneficiary, 15.0, "cinéma");
        //THEN
        assertThrows(DataNotFoundException.class, () -> transactionService.addTransaction(transactionDTO));
        Double wallePayertAfter = userPayer.getWallet();
        Double walleBeneficiarytAfter = userBeneficiary.getWallet();
        assertTrue(wallePayertAfter == wallePayertBefore);
        assertTrue(walleBeneficiarytAfter == walleBeneficiarytBefore);
    }
    //-----------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test addTransactionDescriptionNotConformTest")
    public void addTransactionDescriptionNotConformTest() {
        //GIVEN
        listUserDTO = userService.findAll();
        User userPayer = factory.constructUser(listUserDTO.get(1));
        User userBeneficiary = factory.constructUser(listUserDTO.get(2));
        //WHEN
        String descriptionTooLong =" I am a description a lot a lot a lot much too long";
        TransactionDTO transactionDTO = new TransactionDTO(userPayer, userBeneficiary, 15.0, descriptionTooLong);
        //THEN
        assertThrows(DataNotConformException.class, () -> transactionService.addTransaction(transactionDTO));
    }
    //-----------------------------------------------------------------------------------------------------
}
