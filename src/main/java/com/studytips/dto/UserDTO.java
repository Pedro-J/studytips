package com.studytips.dto;

import com.studytips.entities.User;
import com.studytips.enums.UserProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * User DTO
 *
 *  @author Michael DESIGAUD on 14/02/2016.
 *  @see @link https://github.com/RedFroggy/angular-spring-hmac/tree/angular2
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    private Integer id;

    @NotEmpty
    private String login;

    @NotEmpty
    private List<String> authorities;

    private UserProfile profile;

    public UserDTO(){

    }

    public UserDTO(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.profile = user.getProfile();
        this.authorities = user.getAuthorities();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
