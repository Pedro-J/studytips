package com.studytips.configuration.security;

import com.studytips.configuration.security.hmac.HmacException;
import com.studytips.configuration.security.hmac.HmacSigner;
import com.studytips.entities.User;
import com.studytips.services.UserService;
import com.studytips.services.impl.AuthenticationService;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * Auth token filter
 *
 *  @author Michael DESIGAUD on 14/02/2016.
 *  @see @link https://github.com/RedFroggy/angular-spring-hmac/tree/angular2
 */
public class XAuthTokenFilter extends GenericFilterBean{

    private AuthenticationService authenticationService;
    private UserService userService;

    public XAuthTokenFilter(AuthenticationService authenticationService, UserService userService){
       this.authenticationService = authenticationService;
       this.userService = userService;
    }

    /**
     * Find a cookie which contain a JWT
     * @param request current http request
     * @return Cookie found, null otherwise
     */
    private Cookie findJwtCookie(HttpServletRequest request) {
        if(request.getCookies() == null || request.getCookies().length == 0) {
            return null;
        }
        for(Cookie cookie : request.getCookies()) {
            if(cookie.getName().contains(AuthenticationService.JWT_APP_COOKIE)) {
                return cookie;
            }
        }
        return null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //if is '/studytips/authenticate' or any other page which doesn't contain URI '/studytips'
        if (!request.getRequestURI().contains("/studytips") || request.getRequestURI().contains("/studytips/authenticate")){
            filterChain.doFilter(request, response);
        } else {
            //Any page which contain URI '/studytips', exception of '/studytips/authenticate'
            //Verify if user is logged and valid
            try {

                Cookie jwtCookie = findJwtCookie(request);
                Assert.notNull(jwtCookie,"No jwt cookie found");

                String jwt = jwtCookie.getValue();
                String login = HmacSigner.getJwtClaim(jwt, AuthenticationService.JWT_CLAIM_LOGIN);
                Assert.notNull(login,"No login found in JWT");

                //Get user from cache
                User user = userService.findByLogin(login);
                Assert.notNull(user,"No user found with login: "+login);

                Assert.isTrue(HmacSigner.verifyJWT(jwt, user.getPrivateSecret()),"The Json Web Token is invalid");

                Assert.isTrue(!HmacSigner.isJwtExpired(jwt),"The Json Web Token is expired");

                String csrfHeader = request.getHeader(AuthenticationService.CSRF_CLAIM_HEADER);
                Assert.notNull(csrfHeader,"No csrf header found");

                String jwtCsrf = HmacSigner.getJwtClaim(jwt, AuthenticationService.CSRF_CLAIM_HEADER);
                Assert.notNull(jwtCsrf,"No csrf claim found in jwt");

                //Check csrf token (prevent csrf attack)
                Assert.isTrue(jwtCsrf.equals(csrfHeader));

                this.authenticationService.tokenAuthentication(login);
                filterChain.doFilter(request,response);
            } catch (HmacException | ParseException e) {
                e.printStackTrace();
                response.setStatus(403);
            }
        }

    }
}
