package edu.ap.group10.leapwebapp.security;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.ap.group10.leapwebapp.user.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // attempt authentication of the user
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();

        UserDetails user = userService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token;

        try {
            if (user != null) {
                if (!user.isEnabled()) {
                    throw new DisabledException("This user has been disabled.");
                }
                if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                    throw new BadCredentialsException("Incorrect username or password");
                }
                token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(),
                        user.getAuthorities());
                return token;
            } else {
                throw new AuthenticationCredentialsNotFoundException("User credentials not found");
            }

        } catch (AuthenticationException e) {
            log.error("Login error: ", e);
            token = null;
            return token;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public String onAuthenticationSuccess(Authentication auth) {

        return JWT.create().withClaim("role", auth.getAuthorities().toString())
                .withSubject(auth.getPrincipal().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
    }

    public String newJwt(String token) {
        DecodedJWT jwt = null;
        Algorithm algorithm = Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("JWT verification failed", e);
        }

        if (jwt != null) {
            String role = jwt.getClaim("role").toString();
            role = role.substring(1, role.length() - 1);
            return JWT.create().withClaim("role", role)
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                    .sign(algorithm);
        } else {
            return null;
        }
    }

    public String newUserIdJwt(String id) {
        return JWT.create().withClaim("id", id)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstraints.USERID_SECRET.getBytes()));
    }

    public String checkJwt(String token) {
        DecodedJWT jwt = null;
        Algorithm algorithm = Algorithm.HMAC512(SecurityConstraints.USERID_SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            jwt = verifier.verify(token);
            return jwt.getClaim("id").asString();
        } catch (JWTVerificationException e) {
            log.error("JWT verification of user id failed", e);
            return null;
        }
    }
}