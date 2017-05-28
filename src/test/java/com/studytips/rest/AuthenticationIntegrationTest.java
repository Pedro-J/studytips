package com.studytips.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studytips.builder.MockUsers;
import com.studytips.configuration.security.hmac.HmacSigner;
import com.studytips.configuration.security.hmac.HmacUtils;
import com.studytips.dto.LoginDTO;
import com.studytips.dto.UserDTO;
import com.studytips.enums.UserProfile;
import com.studytips.restControllers.AuthenticationController;
import com.studytips.services.UserService;
import com.studytips.services.impl.AuthenticationService;
import org.apache.commons.codec.binary.Base64;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Authentication integration unit tests
 * Created by Michael DESIGAUD on 16/02/2016.
 */

//@Ignore
@RunWith(SpringRunner.class)
@WebMvcTest( controllers = {AuthenticationController.class})
public class AuthenticationIntegrationTest {

    @Autowired
    private WebApplicationContext context;

/*    @Autowired
    private Filter springSecurityFilterChain;*/

    //Mock to consume spring mvc rest controllers
    private MockMvc mockMVC;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;



    private String url = "http://localhost:8080";

    @Before
    public void setup() {
        this.mockMVC = MockMvcBuilders
                .webAppContextSetup(this.context)
                //.addFilters(springSecurityFilterChain)
                .build();
       MockUsers.mock();
       try {
            given(authenticationService.authenticate(Matchers.any(LoginDTO.class), Matchers.any(HttpServletResponse.class))).
                    willReturn(MockUsers.findByUsername("admin"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Authenticate a user with its credentials.
     *
     * @param login    username
     * @param password password
     * @throws Exception
     */
    public MvcResult authenticate(String login, String password,int status) throws Exception {
        //BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        //Post parameters
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setLogin(login);
        loginDTO.setPassword(password);

        try {
            MvcResult result = mockMVC.perform(post(("/studytips/authenticate"), false)//
                    //Content
                    .contentType(MediaType.APPLICATION_JSON)//
                    .content(toJSON(loginDTO)))//
                    //Fin content
                    //Assertions
                    .andExpect(status().is(status)).andReturn();
            Assert.assertNotNull(result);

            if(result.getResponse().getStatus() == 200) {
                Assert.assertTrue(!result.getResponse().getHeader(HmacUtils.X_SECRET).isEmpty());
                Assert.assertTrue(!result.getResponse().getHeader(HmacUtils.X_TOKEN_ACCESS).isEmpty());
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }


        return null;
    }

    public void logout(MvcResult result,int status) throws Exception{

        String secret = new String(Base64.decodeBase64(result.getResponse().getHeader(HmacUtils.X_SECRET).trim().getBytes()));
        String jwtToken = result.getResponse().getHeader(HmacUtils.X_TOKEN_ACCESS);
        String date = DateTime.now().toDateTimeISO().toString();
        String message = "GEThttp://localhost/studytips/logout"+date;
        String digest = HmacSigner.encodeMac(secret, message, HmacUtils.HMAC_SHA_256);

        mockMVC.perform(get("/studytips/logout", false)
                .header(HmacUtils.AUTHENTICATION, jwtToken)
                .header(HmacUtils.X_DIGEST, digest)
                .header(HmacUtils.X_ONCE, date)
                //Content
                .contentType(MediaType.APPLICATION_JSON))//
                //Fin content
                //Assertions
                .andExpect(status().is(status)).andReturn();
    }

    @Test
    public void authenticationAdminSuccess() throws Exception {
        MvcResult result = authenticate("admin","admin",200);
        UserDTO userDTO = fromJSON(result.getResponse().getContentAsString(),UserDTO.class);
        Assert.assertNotNull(userDTO);
        Assert.assertNotNull(userDTO.getLogin());
        //Assert.assertNull(userDTO.getPassword());
        Assert.assertNotNull(userDTO.getAuthorities());
        Assert.assertNotNull(userDTO.getProfile());
        Assert.assertEquals(userDTO.getProfile(), UserProfile.ADMIN);
    }

    @Test
    public void authenticationUserSuccess() throws Exception {
        MvcResult result = authenticate("user","admin",200);
        UserDTO userDTO = fromJSON(result.getResponse().getContentAsString(),UserDTO.class);
        Assert.assertNotNull(userDTO);
        Assert.assertNotNull(userDTO.getLogin());
        //Assert.assertNull(userDTO.getPassword());
        Assert.assertNotNull(userDTO.getAuthorities());
        Assert.assertNotNull(userDTO.getProfile());
        Assert.assertEquals(userDTO.getProfile(), UserProfile.USER);
    }

    @Test
    public void badCredentials() throws Exception {
        authenticate("user","wrongPassword",403);
    }

    @Test
    public void logoutSuccess() throws Exception {
        MvcResult result = authenticate("admin","admin",200);
        logout(result,200);
    }

    private String toJSON(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    private <T> T fromJSON(String json,Class<T> type) throws IOException {
        return new ObjectMapper().readValue(json,type);
    }

}
