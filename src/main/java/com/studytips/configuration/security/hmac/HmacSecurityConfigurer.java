package com.studytips.configuration.security.hmac;

import com.studytips.configuration.security.XAuthTokenFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * Hmac Security filter configurer
 *
 *  @author Michael DESIGAUD on 15/02/2016.
 *  @see @link https://github.com/RedFroggy/angular-spring-hmac/tree/angular2
 */
public class HmacSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private HmacRequester hmacRequester;

    public HmacSecurityConfigurer(HmacRequester hmacRequester){
        this.hmacRequester = hmacRequester;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        HmacSecurityFilter hmacSecurityFilter = new HmacSecurityFilter(hmacRequester);

        //Trigger this filter before SpringSecurity authentication validator
        builder.addFilterBefore(hmacSecurityFilter, XAuthTokenFilter.class);
    }
}
