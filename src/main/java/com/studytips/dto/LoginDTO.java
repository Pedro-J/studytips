package com.studytips.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Login DTO
 *
 */
public class LoginDTO {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    public LoginDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginDTO(){

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
