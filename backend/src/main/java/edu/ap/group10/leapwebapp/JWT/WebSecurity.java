package edu.ap.group10.leapwebapp.JWT;

import java.security.AuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.MvcMatchersAuthorizedUrl;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import edu.ap.group10.leapwebapp.user.UserDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private CustomAuthenticationProvider authprovider;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
        .authenticationProvider(this.authprovider)
        .userDetailsService(this.userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
        .authenticationProvider(this.authprovider)
        .userDetailsService(this.userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder);
    }
/*
    public WebSecurity(UserDetailsServiceImpl userService, BCryptPasswordEncoder bCryptPasswordEncoder, CustomAuthenticationProvider authprovider) {
        //gets the user detail service and password encoder and instantiates them
        this.userDetailsService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authprovider = authprovider;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Enable csrf when supported, currently csrf makes the form and login work faulty.
        http.csrf().disable();

        // this disables session creation on Spring Security.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //enables cors and handles authorization requests.
        http.cors().and().authorizeRequests()
                //post requests on sign up are permitted by everything.
                .antMatchers(HttpMethod.POST, SecurityConstraints.SIGN_UP_URL).permitAll()

                //makes the login work with a form.
                .and()
                .formLogin()
                .loginPage(SecurityConstraints.SIGN_IN_URL)
                
                //Not implemented yet
                //.defaultSuccessUrl("/")

                //reference for if you fail to log in.
                /*.failureUrl("/user/login-error")
                .permitAll()*/

                //Custom protected endpoints if needed, can add more with the permissions
                .and()
                .authorizeRequests()
                //allows anyone to go to the homepage
                .mvcMatchers("/").permitAll()
                .mvcMatchers(HttpMethod.POST, SecurityConstraints.COMPANY_SIGN_UP).permitAll()
                .mvcMatchers(HttpMethod.POST, SecurityConstraints.COMPANY_APPLICATION_STATUS).permitAll()
                .mvcMatchers(HttpMethod.POST, SecurityConstraints.USER_ADMIN_TEST).permitAll()
                //all other request require authenitcation.
                //.anyRequest().authenticated();
                .anyRequest().permitAll();
                
                
        http.exceptionHandling().accessDeniedPage("/user/acess-denied");
    }

    //configures cors
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        //allows any url to access 
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedMethod("DELETE");
        System.out.println(corsConfiguration.getAllowedMethods());
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}