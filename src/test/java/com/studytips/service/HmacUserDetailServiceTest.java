package com.studytips.service;

import com.studytips.builder.MockUsers;
import com.studytips.configuration.security.SecurityUser;
import com.studytips.dto.UserDTO;
import com.studytips.entities.User;
import com.studytips.repositories.UserRepository;
import com.studytips.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * HmacUserDetailService test
 * Created by Michael DESIGAUD on 15/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class HmacUserDetailServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init(){
        MockUsers.mock();
    }


    @Test
    public void loadByUserName(){
        String username = "admin";


        User user = new User(MockUsers.findByUsername(username));
        user.setPassword("");
        PowerMockito.when(userRepository.findByLogin(username)).thenReturn(user);

        UserDetails userDetails = userService.loadUserByUsername(username);
        Assert.assertNotNull(userDetails);
        Assert.assertTrue(userDetails.getClass().isAssignableFrom(SecurityUser.class));

        SecurityUser securityUser = (SecurityUser) userDetails;
        UserDTO userDTO = MockUsers.findByUsername(username);
        Assert.assertNotNull(userDTO);
        Assert.assertEquals(userDTO.getId(),securityUser.getId());
        Assert.assertEquals(userDTO.getLogin(),securityUser.getUsername());
        Assert.assertEquals(userDTO.getProfile(),securityUser.getProfile());
        //Assert.assertNotNull(userDTO.getPassword());
        Assert.assertNotNull(securityUser.getAuthorities());
        Assert.assertTrue(!securityUser.getAuthorities().isEmpty());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadByWrongUserName(){
        userService.loadUserByUsername("unknown");
    }
}
