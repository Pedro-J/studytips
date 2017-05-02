package com.studytips.configuration.security.hmac;

import javax.servlet.http.HttpServletRequest;

/**
 * Hmac verification interface
 *
 *  @author Michael DESIGAUD on 15/02/2016.
 *  @see @link https://github.com/RedFroggy/angular-spring-hmac/tree/angular2
 */
public interface HmacRequester {

    /**
     * Check if its possible to verify the request
     * @param request htp reqsuest
     * @return true if possible, false otherwise
     */
    Boolean canVerify(HttpServletRequest request);

    /**
     * Get the stored public secret (locally,remotely,cache,etc..)
     * @param iss issuer
     * @return secret key
     */
    String getPublicSecret(String iss);
}
