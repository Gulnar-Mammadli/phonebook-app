package com.mammadli.phonebookapp.api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.mammadli.phonebookapp.api.Controller;;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import com.mammadli.phonebookapp.db.entities.User;
import com.mammadli.phonebookapp.service.UserServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {Controller.class})
@ExtendWith(SpringExtension.class)
class ControllerTest {
    @Autowired
    private Controller controller;

    @MockBean
    private UserServices userServices;

    @Test
    void testCreate() throws Exception {
        User user = new User();
        user.setDeleted(true);
        user.setName("Name");
        user.setPhone("4105551212");
        user.setUserId("42");
        when(this.userServices.create((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setDeleted(true);
        user1.setName("Name");
        user1.setPhone("4105551212");
        user1.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"userId\":\"42\",\"name\":\"Name\",\"phone\":\"4105551212\",\"deleted\":true}"));
    }

    @Test
    void testUpdate() throws Exception {
        User user = new User();
        user.setDeleted(true);
        user.setName("Name");
        user.setPhone("4105551212");
        user.setUserId("42");
        when(this.userServices.update((User) any())).thenReturn(user);

        User user1 = new User();
        user1.setDeleted(true);
        user1.setName("Name");
        user1.setPhone("4105551212");
        user1.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(user1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"userId\":\"42\",\"name\":\"Name\",\"phone\":\"4105551212\",\"deleted\":true}"));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(this.userServices).delete((String) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/*");
        MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testDelete2() throws Exception {
        doNothing().when(this.userServices).delete((String) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/user/*");
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(this.userServices.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/user");
        MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllUsers2() throws Exception {
        when(this.userServices.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/user/user");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGet() throws Exception {
        User user = new User();
        user.setDeleted(true);
        user.setName("Name");
        user.setPhone("4105551212");
        user.setUserId("42");
        when(this.userServices.get((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/*");
        MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}