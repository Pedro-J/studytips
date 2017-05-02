package com.studytips.configuration.security.hmac;

/**
 *
 * Hmac Exception
 *
 *  @author Michael DESIGAUD on 15/02/2016.
 *  @see @link https://github.com/RedFroggy/angular-spring-hmac/tree/angular2
 */
public class HmacException extends Exception{

    public HmacException(String message) {
        super(message);
    }

    public HmacException(String message, Throwable throwable) {
        super(message,throwable);
    }
}
