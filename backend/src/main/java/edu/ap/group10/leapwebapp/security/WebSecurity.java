package edu.ap.group10.leapwebapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import edu.ap.group10.leapwebapp.user.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userDetailsService;
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors().and().authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstraints.SIGN_UP_URL).permitAll()
                .and()
                .formLogin()
                .loginPage(SecurityConstraints.SIGN_IN_URL)

                .failureUrl(SecurityConstraints.SIGN_IN_URL)

                .successForwardUrl(SecurityConstraints.SIGN_IN_URL)
                
                .and()
                .authorizeRequests()

                .mvcMatchers("/").permitAll()
                .mvcMatchers(HttpMethod.POST, SecurityConstraints.COMPANY_SIGN_UP).permitAll()
                .mvcMatchers(HttpMethod.POST, SecurityConstraints.USER_ADMIN_SIGN_UP).permitAll()

                .anyRequest()
                .permitAll();
                //.authenticated();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedMethod("DELETE");
        corsConfiguration.addAllowedMethod("PUT");
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}