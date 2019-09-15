package org.disturbed75.application.controller;

import org.junit.Test;

import static org.disturbed75.application.TestUtil.*;
import static org.disturbed75.application.date.UserDate.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testRegistration() throws Exception {
        mockMvc.perform(get("/registration")
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login")
                .with(userHttpBasic(user1)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    //@Test
    public void testAuth() throws Exception {
        mockMvc.perform(get("/login")
                .with(userHttpBasic(admin)))
                .andDo(print())
                .andExpect(redirectedUrl("http://localhost/greeting"));
    }
}