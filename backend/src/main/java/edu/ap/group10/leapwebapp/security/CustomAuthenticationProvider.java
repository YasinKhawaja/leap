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

import edu.ap.group10.leapwebapp.user.User;
import edu.ap.group10.leapwebapp.user.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String CLAIM_COMPANY = "company";
    private static final String CLAIM_ROLE = "role";
    private static final String ISSUER = "LEAP";

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();

        UserDetails user = userService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = null;

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
                System.out.println(token);
                return token;
            } else {
                throw new AuthenticationCredentialsNotFoundException("User credentials not found");
            }

        } catch (AuthenticationException e) {
            log.error("Login error: ", e);
            return token;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public String onAuthenticationSuccess(Authentication auth) {
        User user = userService.findUserByUsername(auth.getPrincipal().toString());

        if (user.getCompany() != null) {
            return JWT.create().withClaim(CLAIM_ROLE, auth.getAuthorities().toString())
                    .withClaim(CLAIM_COMPANY, user.getCompany().getId().toString())
                    .withSubject(auth.getPrincipal().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                    .withIssuer(ISSUER).sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
        } else {
            return JWT.create().withClaim(CLAIM_ROLE, auth.getAuthorities().toString())
                    .withSubject(auth.getPrincipal().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                    .withIssuer(ISSUER).sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
        }

    }

    public String newJwt(String token) {
        DecodedJWT jwt = verifyJWTUser(token);

        if (jwt != null) {
            String role = jwt.getClaim(CLAIM_ROLE).toString();
            role = role.substring(1, role.length() - 1);
            String company = jwt.getClaim(CLAIM_COMPANY).toString();
            company = company.substring(1, company.length() - 1);

            return JWT.create().withClaim(CLAIM_ROLE, role).withClaim(CLAIM_COMPANY, company)
                    .withSubject(jwt.getSubject()).withIssuer(jwt.getIssuer())
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
        } else {
            return null;
        }
    }

    public String newUserIdJwt(String id) {
        return JWT.create().withClaim("id", id)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstraints.USERID_SECRET.getBytes()));
    }

    public String checkUserID(String token) {
        DecodedJWT jwt = verifyJWTPasswordReset(token);
        return jwt.getClaim("id").asString();
    }

    public DecodedJWT verifyJWTPasswordReset(String token) {
        DecodedJWT jwt = null;
        Algorithm algorithm = Algorithm.HMAC512(SecurityConstraints.USERID_SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("JWT for password reset failed", e);
        }

        return jwt;
    }

    public DecodedJWT verifyJWTUser(String token) {
        DecodedJWT jwt = null;
        Algorithm algorithm = Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        try {
            jwt = verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("JWT for Authentication failed", e);
        }

        return jwt;
    }
}