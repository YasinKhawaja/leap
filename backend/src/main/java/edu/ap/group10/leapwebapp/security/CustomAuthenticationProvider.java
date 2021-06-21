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
import edu.ap.group10.leapwebapp.user.UserRepository;
import edu.ap.group10.leapwebapp.user.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Sander Standaert
 */

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String CLAIM_COMPANY = "company";
    private static final String CLAIM_ROLE = "role";
    private static final String ISSUER = "LEAP";

    /**
     * Documentation on this method is described in the Interface see
     * 
     * @see AuthenticationProvider#authenticate(Authentication)
     * 
     * @exception DisabledException                          Is thrown when the user
     *                                                       is disabled.
     * @exception BadCredentialsException                    Is thrown when the
     *                                                       input password isn't
     *                                                       the same as the saved
     *                                                       password, this is
     *                                                       checked by encrypting
     *                                                       the input password and
     *                                                       checking this encyrpted
     *                                                       password with the one
     *                                                       that's saved.
     * @exception AuthenticationCredentialsNotFoundException Is thrown when the
     *                                                       username wasn't found.
     * 
     * @return <code>UsernamePasswordAuthenticationToken</code> that includes the
     *         username password and role of the user.
     * @see UsernamePasswordAuthenticationToken#UsernamePasswordAuthenticationToken(Object,
     *      Object, java.util.Collection)
     */
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();

        UserDetails user = userService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken token = null;

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
    }

    /**
     * @see AuthenticationProvider#supports()
     * 
     * @return Allows the {@link #authenticate(Authentication)} method to return an
     *         object of the <code>UsernamePasswordAuthenticationToken</code> class.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    /**
     * @param auth The <code>Authentication</code> object passed by the authenticate
     *             method.
     * @see #authenticate()
     * 
     *      Finds the user out of the <code>Authentication</code> object so we can
     *      assign the <code>JWT</code> with the claims
     * @see com.auth0.jwt.JWTCreator.Builder#withClaim(String, String) The constant
     *      {@link #CLAIM_ROLE} this claim will be assigned with the value of the
     *      user's role through the <code>Authentication</code> object.
     *      {@link Authentication#getAuthorities()}
     * @see com.auth0.jwt.JWTCreator.Builder#withClaim(String, String) The constant
     *      {@link #CLAIM_COMPANY} this claim will be assigned with the value of the
     *      <code>User</code> companyid. {@link User#getCompany()}
     *      <strong>Note</strong> if the user's company is undefined then this means
     *      they're an application admin, their <code>JWT</code> will not include
     *      the company claim.
     * @see com.auth0.jwt.JWTCreator.Builder#withSubject(String) The subject of this
     *      <code>JWT</code> includes the username that was assigned through the
     *      <code>Authentication</code> object.
     *      {@link Authentication#getPrincipal()}
     * @see com.auth0.jwt.JWTCreator.Builder#withExpiresAt(Date) The expiration date
     *      of the token, makes use of the {@link Date#Date(long)} method. Is
     *      assigned by using {@link System#currentTimeMillis()} added with a
     *      <class>Long</class> number in milliseconds, the value of
     *      {@link SecurityConstraints#EXPIRATION_TIME} is equal to 15 minutes.
     * @see com.auth0.jwt.JWTCreator.Builder#withIssuer(String) The constant
     *      {@link #ISSUER} is a tag that is given with the token to show who
     *      created(issued) it.
     * @see com.auth0.jwt.JWTCreator.Builder#sign(Algorithm) Signs the token using
     *      the {@link Algorithm#HMAC512(String)} algorithm that uses the predefined
     *      {@link SecurityConstraints#SECRET}.
     * 
     * @return Returns a new instance of <code>JWT</code> as explained above.
     * @see JWT#create()
     */
    public String onAuthenticationSuccess(Authentication auth) {
        User user = userService.findUserByUsername(auth.getPrincipal().toString());

        if (user.getCompany() != null) {
            if (user.getCompany().getApproved().booleanValue()) {
                return JWT.create().withClaim(CLAIM_ROLE, auth.getAuthorities().toString())
                        .withClaim(CLAIM_COMPANY, user.getCompany().getId().toString())
                        .withSubject(auth.getPrincipal().toString())
                        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                        .withIssuer(ISSUER).sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
            } else {
                return null;
            }
        } else {
            return JWT.create().withClaim(CLAIM_ROLE, auth.getAuthorities().toString())
                    .withSubject(auth.getPrincipal().toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                    .withIssuer(ISSUER).sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
        }

    }

    /**
     * @param token The value of the <code>JWT</code> that was assigned to the user.
     * @see #onAuthenticationSuccess(Authentication)
     * 
     * @param username This parameter is "Optional", it's sent when the username of
     *                 the user was changed during the request. If this isn't the
     *                 case then the username as saved in the previous
     *                 <code>JWT</code> is used.
     * 
     * @see #verifyJWTUser(String) verifies and decodes the JWT and assigns it to a
     *      <code>DecodedJWT</code> variable.
     * 
     *      Finds the role of the user by using the
     *      {@link UserRepository#findByUsername(String)} to return the
     *      <code>User</code> object which can then return the role of this user.
     *      The company will be used as saved in the previous token.
     * 
     * @return Returns a new instance of <code>JWT</code> as shown in
     *         {@link #onAuthenticationSuccess(Authentication)}.
     */
    public String newJwt(String token, String username) {
        DecodedJWT jwt = verifyJWTUser(token);
        String usernameToken;

        if (!username.equals("")) {
            usernameToken = username;
        } else {
            usernameToken = jwt.getSubject();
        }

        if (jwt != null) {
            String role = userRepository.findByUsername(usernameToken).getAuthorities().toString();
            String company = jwt.getClaim(CLAIM_COMPANY).toString();
            company = company.substring(1, company.length() - 1);

            return JWT.create().withClaim(CLAIM_ROLE, role).withClaim(CLAIM_COMPANY, company).withSubject(usernameToken)
                    .withIssuer(jwt.getIssuer())
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SecurityConstraints.SECRET.getBytes()));
        } else {
            return null;
        }
    }

    /**
     * @param token The value of the <code>JWT</code> that was assigned to the user.
     * @see #onAuthenticationSuccess(Authentication)
     * 
     * @see #verifyJWTUser(String) verifies and decodes the JWT and assigns it to a
     *      <code>DecodedJWT</code> variable.
     * 
     * @return Returns the role of the user as saved in the <code>DecodedJWT</code>
     */
    public String getRole(String token) {
        DecodedJWT jwt = verifyJWTUser(token);
        return jwt.getClaim(CLAIM_ROLE).toString();
    }

    public String newUserIdJwt(String id) {
        return JWT.create().withClaim("id", id)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstraints.EXPIRATION_TIME_PASSWORDRESET))
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