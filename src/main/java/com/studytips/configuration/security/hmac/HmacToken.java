package com.studytips.configuration.security.hmac;

/**
 * HMAC Token
 *  @author Michael DESIGAUD on 15/02/2016.
 *  @see @link https://github.com/RedFroggy/angular-spring-hmac/tree/angular2
 */
public class HmacToken {

    private String jwtID;

    private String secret;

    private String jwt;


    public HmacToken(String jwtID, String secret, String jwt) {
        this.jwtID = jwtID;
        this.secret = secret;
        this.jwt = jwt;
    }

    public String getJwtID() {
        return jwtID;
    }

    public String getSecret() {
        return secret;
    }

    public String getJwt() {
        return jwt;
    }
}
