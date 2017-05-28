package com.studytips.rest;

import com.studytips.dto.LoginDTO;
import com.studytips.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author Created by comp-dev on 5/27/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TemplateTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test() {
        ResponseEntity<UserDTO> response =
        this.restTemplate.postForEntity(
                "/studytips/authenticate", new LoginDTO("admin","admin"), UserDTO.class, false);

        Assert.assertNotNull(response);
    }

}