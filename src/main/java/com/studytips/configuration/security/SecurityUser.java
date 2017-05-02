package com.studytips.configuration.security;

import com.studytips.enums.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Security spring security user
 *
 *  @author Michael DESIGAUD on 15/02/2016.
 *  @see @link https://github.com/RedFroggy/angular-spring-hmac/tree/angular2
 */
public class SecurityUser extends User{

    private Integer id;

    private UserProfile profile;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUser(Integer id, String username, String password, UserProfile profile, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.profile = profile;
    }

    public Integer getId() {
        return id;
    }

    public UserProfile getProfile() {
        return profile;
    }
}
