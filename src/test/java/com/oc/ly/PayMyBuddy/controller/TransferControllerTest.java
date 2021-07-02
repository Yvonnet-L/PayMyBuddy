package com.oc.ly.PayMyBuddy.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username="lolo@email.com")
    @Test
    @DisplayName("Test /contact")
    public void transferAttributesExistTest() throws Exception {

        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists( "admin", "errorMessage", "currentPage", "motCle"));
    }
    //-------------------------------------------------------------------------------------------------------
    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /deleteAccount")
    public void deleteAccountTest() throws Exception {
        mockMvc.perform(get("/deleteAccount")
                .param("id", "3"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------

    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /addBankAccount")
    public void addBankAccountTest() throws Exception {
        mockMvc.perform(post("/addBankAccount")
                .param("rib", "fr 1111 2222 3333 44444"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------

    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /addTransfer")
    public void addTransferCreditTest() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "200.0")
                .param("type", "CREDIT_WALLET"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------

    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /addTransfer")
    public void addTransferDeditOkTest() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "20.0")
                .param("type", "DEBIT_WALLET"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /addTransfer")
    public void addTransferDebitNotPossibleTest() throws Exception {
        mockMvc.perform(post("/addTransfer")
                .param("rib", "fr 1111 2222 3333 44444")
                .param("amount", "10000.0")
                .param("type", "DEBIT_WALLET"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /transfer")
    public void transferTest() throws Exception {
        mockMvc.perform(get("/transfer"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists( "bankAccounts",
                        "transfers", "currentPage"));
    }
    //--------------------------------------------------------------------------------------------------------
}
