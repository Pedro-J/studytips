package com.studytips.services.impl;

import com.studytips.configuration.security.hmac.HmacRequester;
import com.studytips.entities.User;
import com.studytips.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Hmac Requester services
 * Created by Michael DESIGAUD on 16/02/2016.
 */
@Service
public class DefaultHmacRequester implements HmacRequester {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean canVerify(HttpServletRequest request) {
        return request.getRequestURI().contains("/studytips") && !request.getRequestURI().contains("/studytips/authenticate");
    }

    @Override
    public String getPublicSecret(String iss) {
        User user = userRepository.findOne(Integer.valueOf(iss));
        if(user != null){
            return user.getPublicSecret();
        }
        return null;
    }
}
