package com.oc.ly.PayMyBuddy.service;

import com.oc.ly.PayMyBuddy.dto.BankAccountDTO;
import com.oc.ly.PayMyBuddy.dto.UserDTO;
import com.oc.ly.PayMyBuddy.model.BankAccount;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class BankAccountServiceTest {

    @Autowired
    private IBankAccountService bankAccountService;

    @Autowired
    private IUserService userService;

    public Factory factory = new Factory();

    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test findBankAccountByUser")
    public void findBankAccountByUserTest(){
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        List<BankAccountDTO> listBankAccount = bankAccountService.findBankAccountByUser(userDTO);
        assertTrue(listBankAccount.size()>0);
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test addAccount ")
    public void addAccountTest(){
        //GIVEN
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        User user = factory.constructUser(userDTO);
        String rib = "fr 1111 2222 3333 4444";
        //WHEN
        BankAccount newBankAccount = new BankAccount(user,rib);
        BankAccountDTO newBankAccountDTO = factory.constructBankDTO(newBankAccount);
        BankAccountDTO bankAccountAddDTO = bankAccountService.addAccount(rib,userDTO);
        //THEN
        assertEquals(bankAccountAddDTO.getRib(), newBankAccountDTO.getRib());

    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test deleteAccount ")
    public void deleteAccountTest() {
        //GIVEN
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO = userService.findUserByEmail(emailSession);
        User user = factory.constructUser(userDTO);
        //WHEN
        List<BankAccountDTO> listBankAccountDTOBefore = bankAccountService.findBankAccountByUser(userDTO);
        bankAccountService.deleteAccount(listBankAccountDTOBefore.get(1).getIdBankAccount());
        List<BankAccountDTO> listBankAccountDTOAfter = bankAccountService.findBankAccountByUser(userDTO);
        //THEN
        assertEquals(listBankAccountDTOBefore.size() - 1, listBankAccountDTOAfter.size());
    }
    //--------------------------------------------------------------------------------------------------------
}
