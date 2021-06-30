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
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /transaction")
    public void transactionTest() throws Exception {
        mockMvc.perform(get("/transaction"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists( "friends","transactions",
                                                    "description", "currentPage", "friend"));
    }
    //--------------------------------------------------------------------------------------------------------
    //
    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /transaction")
    public void addTransactionTest() throws Exception {
        mockMvc.perform(post("/transaction")
                .param("friendEmail","fanny@email.com")
                .param("amount", "25.0")
                .param("description", "cinéma"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------

    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /deleteTransaction")
    public void deleteTransactionTest() throws Exception {
        mockMvc.perform(get("/deleteTransaction")
                .param("id", "3")
                .param("page", "0"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------
}
