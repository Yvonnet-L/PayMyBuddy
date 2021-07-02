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
public class ProfilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //--------------------------------------------------------------------------------------------------------

    @WithMockUser(username="lolo@email.com")
    @Test
    @DisplayName("Test /profile")
    public void profileAttributesExistTest() throws Exception {

        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists( "firstName", "lastName", "email", "password",
                        "confirmation"));

    }
    //-------------------------------------------------------------------------------------------------------
}
