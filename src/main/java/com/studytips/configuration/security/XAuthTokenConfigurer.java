package com.studytips.configuration.security;

import com.studytips.services.UserService;
import com.studytips.services.impl.AuthenticationService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Auth token configurer
 *
 *  @author created by Michael DESIGAUD on 14/02/2016.
 *  @author modified by comp-dev
 *
 *  @see @link https://github.com/RedFroggy/angular-spring-hmac/tree/angular2
 */
public class XAuthTokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private AuthenticationService authenticationService;

    private UserService userService;

    public XAuthTokenConfigurer(AuthenticationService authenticationService, UserService userService){
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        XAuthTokenFilter xAuthTokenFilter = new XAuthTokenFilter(authenticationService, userService);

        //Trigger this filter before SpringSecurity authentication validator
        builder.addFilterBefore(xAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
