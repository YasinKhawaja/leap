package edu.ap.group10.leapwebapp.JWT;

public class SecurityConstraints {
    //jwt secret
    public static final String SECRET = "Is_this,32 characters long or no";
    //15 minute expiration time on token
    public static final long EXPIRATION_TIME = 900_000;
    //Needed to give the token the bearer prefix
    public static final String TOKEN_PREFIX = "Bearer ";
    //authorization token
    public static final String HEADER_STRING = "Authorization";
    //sign up url
    public static final String SIGN_UP_URL = "/user/register";
    //log in url
    public static final String SIGN_IN_URL = "/user/login";
    //sign up company url
    public static final String COMPANY_SIGN_UP = "/companies/register";
    //DELETE LATER
    public static final String COMPANY_APPLICATION_STATUS = "/companies/register/26/applicationStatus";
    public static final String USER_ADMIN_TEST = "/useradmin/register";
}
