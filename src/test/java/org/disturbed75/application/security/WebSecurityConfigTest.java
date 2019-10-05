package org.disturbed75.application.security;

import org.disturbed75.application.controller.AbstractControllerTest;
import org.disturbed75.application.date.UserDate;
import org.disturbed75.application.entity.User;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import static org.disturbed75.application.TestUtil.userHttpBasic;
import static org.disturbed75.application.date.UserDate.admin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class WebSecurityConfigTest extends AbstractControllerTest {

   /* @Test
    @WithMockUser(username = "user1", password = "pwd", roles = "USER")
    public void mytest1() throws Exception {
        mockMvc.perform(get("/someApi"))
                .andExpect(status().isOk());
    }*/

    @WithMockUser(username = "admin", password = "admin")
    @Test
    public void testAuthOld() throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("UserPassword");
        User us = admin;
        us.setPassword(encodedPassword);
        mockMvc.perform(post("/login")
            //    .with(userHttpBasic(admin)))
                .with(userHttpBasic(us)))
                .andDo(print())
                .andExpect(redirectedUrl("http://localhost/greeting"));
    }
}
