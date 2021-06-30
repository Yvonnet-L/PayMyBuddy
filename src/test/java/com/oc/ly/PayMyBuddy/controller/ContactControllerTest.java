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
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //--------------------------------------------------------------------------------------------------------

    @WithMockUser(username="lolo@email.com")
    @Test
    @DisplayName("Test /contact")
    public void contactAttributesExistTest() throws Exception {

        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists( "friends", "notFriends", "currentPage", "pages",
                        "motCle"));

    }
    //-------------------------------------------------------------------------------------------------------

    @WithMockUser(username="lolo@email.com", roles={"ADMIN"})
    @Test
    @DisplayName("Test /deleteContact")
    public void deleteContactTest() throws Exception {
        mockMvc.perform(get("/deleteContact")
                .param("idFriend", "2"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------

    @WithMockUser(username="lolo@email.com")
    @Test
    @DisplayName("Test /addContact")
    public void addContactTest() throws Exception {
        mockMvc.perform(get("/addContact")
                .param("idFriend", "2"))
                .andExpect(status().isFound());
    }
    //--------------------------------------------------------------------------------------------------------

}
