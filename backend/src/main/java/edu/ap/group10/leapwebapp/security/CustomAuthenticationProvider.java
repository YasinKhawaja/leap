package edu.ap.group10.leapwebapp.security;

import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.ap.group10.leapwebapp.user.UserLeap;
import edu.ap.group10.leapwebapp.user.UserRepository;
import edu.ap.group10.leapwebapp.useradmin.Useradmin;
import edu.ap.group10.leapwebapp.useradmin.UseradminRepository;
import javassist.NotFoundException;

//Keep comment section for now since we'll need it if we switch to the given JWT System

/*
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.authentication.AuthenticationProviderBeanDefinitionParser;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.ap.group10.leapwebapp.JWT.SecurityConstraints;

import edu.ap.group10.leapwebapp.user.UserDetailsServiceImpl;*/

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UseradminRepository useradminRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //attempt authentication of the user
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();

        UserLeap userLeap = userRepository.findByUsername(username);
        Useradmin useradmin = useradminRepository.findByUsername(username);

        //Make exceptions constant, put them in a class
        UsernamePasswordAuthenticationToken token;

        if(userLeap != null) {
            if(!userLeap.isEnabled()){
                throw new DisabledException("This user has been disabled.");
            }
            if(!bCryptPasswordEncoder.matches(password, userLeap.getPassword())) {
                throw new BadCredentialsException("Incorrect username or password");
            }
            token = new UsernamePasswordAuthenticationToken(userLeap, password, userLeap.getAuthorities());
            //remove
            System.out.println(token);
            return token;
        }
        else if(useradmin != null){
            if(!useradmin.isEnabled()){
                throw new DisabledException("This admin has been disabled.");
            }
            if(!bCryptPasswordEncoder.matches(password, useradmin.getPassword())) {
                throw new BadCredentialsException("Incorrect password.");
            }
            token = new UsernamePasswordAuthenticationToken(useradmin, password, useradmin.getAuthorities());
            //remove
            System.out.println(token);
            return token;
        }
        else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    /*@Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));

        String body = ((User) auth.getPrincipal()).getUsername() + " " + token;

        res.getWriter().write(body);
        res.getWriter().flush();
    }*/
}