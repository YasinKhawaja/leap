package edu.ap.group10.leapwebapp.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.security.web.server.header.StrictTransportSecurityServerHttpHeadersWriter;
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

    public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDetailsService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
            .headers()
                .referrerPolicy(ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN)
                .and()
                .httpStrictTransportSecurity()
                    .includeSubDomains(true)
                    .maxAgeInSeconds(31536000);

        http.cors()
            .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstraints.SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstraints.SIGN_IN_URL).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstraints.COMPANY_SIGN_UP).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstraints.USER_ADMIN_SIGN_UP).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstraints.PASSWORD_RESET).permitAll()
                .antMatchers(HttpMethod.PUT, SecurityConstraints.PASSWORD_RESET).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstraints.APPLICATION_ADMIN).permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                        .addFilter(new JWTAuthenticationFilter(authprovider))
                        .addFilter(new JWTAuthorizationFilter(authenticationManager()));

        http.csrf()
            .ignoringAntMatchers(
                                SecurityConstraints.SIGN_UP_URL,
                                SecurityConstraints.SIGN_IN_URL,
                                SecurityConstraints.COMPANY_SIGN_UP,
                                SecurityConstraints.USER_ADMIN_SIGN_UP,
                                SecurityConstraints.PASSWORD_RESET,
                                SecurityConstraints.APPLICATION_ADMIN
                                )
            .csrfTokenRepository(csrfTokenRepository());
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    private CsrfTokenRepository csrfTokenRepository() {
        CookieCsrfTokenRepository tokenRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();
        tokenRepository.setCookiePath("/");
        return tokenRepository;
	}

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://groep-10.project.ap.be"));
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}