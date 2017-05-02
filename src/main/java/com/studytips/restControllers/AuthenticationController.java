package com.studytips.restControllers;

import com.studytips.dto.LoginDTO;
import com.studytips.dto.UserDTO;
import com.studytips.services.impl.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Authentication restControllers controller
 *
 *  @author Michael DESIGAUD on 14/02/2016.
 *  @see @link https://github.com/RedFroggy/angular-spring-hmac/tree/angular2
 */
@RestController
@RequestMapping(value = "/studytips")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public UserDTO authenticate(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws Exception{
        return authenticationService.authenticate(loginDTO, response);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(){
        authenticationService.logout();
    }
}
