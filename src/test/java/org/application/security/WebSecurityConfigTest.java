package org.application.security;

import org.application.date.UserDate;
import org.application.controller.AbstractControllerTest;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class WebSecurityConfigTest extends AbstractControllerTest {


    @WithMockUser(username = "admin", password = "admin")
    @Test
    public void testAuthOld() throws Exception {
        mockMvc.perform(post("/login")
        .param("username" , UserDate.user4.getUsername())
        .param("password", UserDate.user4.getPassword()))
                .andDo(print())
                .andExpect(redirectedUrl("/greeting"));
    }
}
