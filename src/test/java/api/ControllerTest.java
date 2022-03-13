package api;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.mammadli.phonebookapp.api.Controller;
import com.mammadli.phonebookapp.db.entities.User;
import com.mammadli.phonebookapp.service.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
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

    @Test
    void testGet2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    void testCreate() throws Exception {
        User user = new User();
        user.setDeleted(true);
        user.setName("Name");
        user.setPhone("4105551212");
        user.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
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
    void testUpdate() throws Exception {
        User user = new User();
        user.setDeleted(true);
        user.setName("Name");
        user.setPhone("4105551212");
        user.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.controller)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }
}