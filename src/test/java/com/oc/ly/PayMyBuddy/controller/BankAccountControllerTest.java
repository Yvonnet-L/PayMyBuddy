package com.oc.ly.PayMyBuddy.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.ly.PayMyBuddy.dto.BankAccountDTO;
import com.oc.ly.PayMyBuddy.model.BankAccount;
import com.oc.ly.PayMyBuddy.model.MyUserDetails;
import com.oc.ly.PayMyBuddy.model.User;
import com.oc.ly.PayMyBuddy.repository.BankAccountRepository;
import com.oc.ly.PayMyBuddy.repository.UserRepository;
import com.oc.ly.PayMyBuddy.tool.Factory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class BankAccountControllerTest {

    @Mock
    SecurityContext securityContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    public MyUserDetails myUserDetails;

    private static Logger logger = LogManager.getLogger(BankAccountControllerTest.class);

    public Factory factory= new Factory();

    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test reponse 200 sur getBanks")
    public void findAllBankTest() throws Exception {
        //String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();

        mockMvc.perform(get("/management/bankAccounts")).andExpect(status().isOk());
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username="lolo@email.com")
    @Test
    @DisplayName("Test reponse 201 sur ajout d'une bank conforme")
    public void addBankTest() throws Exception {
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(emailSession);
        BankAccountDTO bankToCreateDTO = new BankAccountDTO(user, "FR 1454 4547 4547 4547");

       mockMvc.perform(post("/management/bankAccount")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bankToCreateDTO)))
                .andExpect(status().isCreated());

    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test reponse 400 sur ajout d'une bank null")
    public void addBankNullTest() throws Exception {

        BankAccount bankNull = null;

        mockMvc.perform(post("/management/bankAccount")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bankNull)))
                .andExpect(status().isBadRequest());

    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test reponse 201 sur la modification d'une bank")
    public void upDateBankTest() throws Exception {
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(emailSession);
        BankAccount bankToCreate = new BankAccount(1,user,"FR 1454 4547 4547 4547");

        mockMvc.perform(put("/management/bankAccount")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bankToCreate)))
                .andExpect(status().isCreated());
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username = "lolo@email.com")
    @Test
    @DisplayName("Test reponse 200 sur la suppression d'un banAcount")
    public void deleteDankTest() throws Exception {
        String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findUserByEmail(emailSession);
        BankAccount bankToCreate = new BankAccount(1,user,"FR 1454 4547 4547 4547");

        mockMvc.perform(delete("/management/bankAccount")
                .contentType("application/json")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bankToCreate)))
                .andExpect(status().isOk());
    }

    //--------------------------------------------------------------------------------------------------------
}
