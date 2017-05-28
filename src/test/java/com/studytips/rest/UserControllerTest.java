package com.studytips.rest;

import com.studytips.restControllers.UserController;
import com.studytips.services.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author Created by comp-dev on 5/21/17.
 */

@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private MockMvc mockMVC;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        this.mockMVC = MockMvcBuilders
                .webAppContextSetup(this.context)
                .build();

    }

    @Test
    public void getUsers() throws Exception {
        MvcResult result = mockMVC.perform(get(("/studytips/users/all"), false)//
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().is(200)).andDo(MockMvcResultHandlers.print()).andReturn();

        System.out.print("hue: "+result.getResponse().getContentAsString());
        Assert.assertNotNull(result);

    }

}
