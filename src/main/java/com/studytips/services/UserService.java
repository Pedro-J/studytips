package com.studytips.services;

import com.studytips.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by comp-dev on 4/21/17.
 */
public interface UserService extends GenericService<User>, UserDetailsService {
    User findByLogin(String login);
}
