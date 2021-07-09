package com.oc.ly.PayMyBuddy.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class JoinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //-------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test /join")
    public void joinAttributesExistTest() throws Exception {

        mockMvc.perform(get("/join"))
                .andExpect(status().isOk());
    }
    //-------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test post /join")
    public void postNewUserTest() throws Exception {
        mockMvc.perform(post("/join")
                .param("firstName","Nathy")
                .param("lastName", "Do")
                .param("email", "nathy@email.com")
                .param("password", "0123456789")
                .param("confirmation", "0123456789"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------
}
