package com.oc.ly.PayMyBuddy.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class homeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //--------------------------------------------------------------------------------------------------------
    @Test
    @DisplayName("Test /login")
    public void loginTest() throws Exception {
        //String emailSession = SecurityContextHolder.getContext().getAuthentication().getName();
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username="lolo@email.com")
    @Test
    @DisplayName("Test /home")
    public void homeAttributesExistTest() throws Exception {

        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists( "friends", "transactions", "transfers", "refunds",
                                                     "firstName", "wallet", "currentPage"));

    }
    //--------------------------------------------------------------------------------------------------------

    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /save")
    public void saveTest() throws Exception {
        mockMvc.perform(get("/save"))
                .andExpect(status().isOk());
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /index")
    public void indexTest() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk());
    }
    //--------------------------------------------------------------------------------------------------------
    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /logout")
    public void logoutTest() throws Exception {
        mockMvc.perform(get("/logout")
                .param("HttpServletRequest", "request")
                .param( "HttpServletResponse", "response"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------

    //--------------------------------------------------------------------------------------------------------


}


