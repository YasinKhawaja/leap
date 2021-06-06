package edu.ap.group10.leapwebapp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import edu.ap.group10.leapwebapp.user.User;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private CustomAuthenticationProvider authProvider;

    public JWTAuthenticationFilter(CustomAuthenticationProvider authProvider) {
        this.authProvider = authProvider;

        setFilterProcessesUrl("/api/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        return authProvider.authenticate(
                new UsernamePasswordAuthenticationToken(req.getParameter("username"), req.getParameter("password")));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException {
        String token = authProvider.onAuthenticationSuccess(auth);
        String body = ((User) auth.getPrincipal()).getUsername() + " " + token;
        res.getWriter().write(body);
        res.getWriter().flush();
    }
}
