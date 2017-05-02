package com.studytips.service;

import com.studytips.builder.MockUsers;
import com.studytips.configuration.security.SecurityUser;
import com.studytips.configuration.security.hmac.HmacException;
import com.studytips.dto.LoginDTO;
import com.studytips.dto.UserDTO;
import com.studytips.entities.User;
import com.studytips.enums.UserProfile;
import com.studytips.repositories.UserRepository;
import com.studytips.services.impl.AuthenticationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Authentication service test
 * Created by Michael DESIGAUD on 15/02/2016.
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.crypto.*")
@PrepareForTest({SecurityContextHolder.class})
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HttpServletResponse httpResponse;

    private SecurityUser getSecurityUser(String login, String password){
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new SecurityUser(1,login,password, UserProfile.ADMIN,authorities);
    }

    @Before
    public void init(){
        MockUsers.mock();
    }

    @Test
    public void authenticate() throws HmacException {

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setLogin("admin");
        loginDTO.setPassword("frog");

        SecurityUser securityUser = getSecurityUser(loginDTO.getLogin(),loginDTO.getPassword());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getLogin(),loginDTO.getPassword());

        PowerMockito.when(userDetailsService.loadUserByUsername(loginDTO.getLogin())).thenReturn(securityUser);
        PowerMockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class))).thenReturn(token);
        User user = new User(MockUsers.findById(securityUser.getId()));
        PowerMockito.when(userRepository.findOne(Mockito.anyInt())).thenReturn(user);
        PowerMockito.when(userRepository.saveAndFlush(user)).thenReturn(null);

        UserDTO userDTO = authenticationService.authenticate(loginDTO, httpResponse);
        Assert.assertNotNull(userDTO);
        Assert.assertEquals(userDTO.getLogin(),loginDTO.getLogin());
        Assert.assertNotNull(userDTO.getAuthorities());
        Assert.assertTrue(!userDTO.getAuthorities().isEmpty());

        Mockito.verify(authenticationManager,Mockito.times(1)).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        Mockito.verify(userDetailsService,Mockito.times(1)).loadUserByUsername(loginDTO.getLogin());
        Mockito.verify(httpResponse,Mockito.times(3)).setHeader(Mockito.anyString(),Mockito.anyString());
    }

    @Test
    public void logout(){

        Integer userId = 1;

        UserDTO userDTO = MockUsers.findById(userId);
        Assert.assertNotNull(userDTO);
        //userDTO.setPublicSecret("secretKey");
        //Assert.assertNotNull(userDTO.getPublicSecret());

        PowerMockito.mockStatic(SecurityContextHolder.class);
        UsernamePasswordAuthenticationToken passwordAuthenticationToken = PowerMockito.mock(UsernamePasswordAuthenticationToken.class);
        SecurityContextImpl securityContext = PowerMockito.mock(SecurityContextImpl.class);

        Mockito.when(securityContext.getAuthentication()).thenReturn(passwordAuthenticationToken);
        Mockito.when(passwordAuthenticationToken.isAuthenticated()).thenReturn(true);
        Mockito.when(SecurityContextHolder.getContext()).thenReturn(securityContext);

        Mockito.when(passwordAuthenticationToken.getPrincipal())
                .thenReturn(new SecurityUser(1,"login","password",UserProfile.ADMIN, Arrays.asList(new SimpleGrantedAuthority("ADMIN"))));

        authenticationService.logout();

        userDTO = MockUsers.findById(userId);
        Assert.assertNotNull(userDTO);
        //Assert.assertNull(userDTO.getPublicSecret());

    }

    @Test
    public void authenticateByToken(){

        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setLogin("admin");
        loginDTO.setPassword("frog");

        SecurityUser securityUser = getSecurityUser(loginDTO.getLogin(),loginDTO.getPassword());
        PowerMockito.when(userDetailsService.loadUserByUsername(loginDTO.getLogin())).thenReturn(securityUser);

        authenticationService.tokenAuthentication(loginDTO.getLogin());
        Assert.assertNotNull(SecurityContextHolder.getContext());
        Assert.assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        Assert.assertNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Assert.assertNotNull(SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().isAssignableFrom(SecurityUser.class));
        Assert.assertEquals(SecurityContextHolder.getContext().getAuthentication().getPrincipal(),securityUser);
    }
}
