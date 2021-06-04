package edu.ap.group10.leapwebapp.security;

public class SecurityConstraints {
    
    private SecurityConstraints(){}
    
    public static final String SECRET = "Is_this,32 characters long or no";
    public static final String USERID_SECRET = ",A32_Characters-Long_String_Yepp";
    public static final long EXPIRATION_TIME = 900_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String SIGN_UP_URL = "/user";
    public static final String SIGN_IN_URL = "/user/login";
    public static final String COMPANY_SIGN_UP = "/companies";
    public static final String USER_ADMIN_SIGN_UP = "/useradmin";
    public static final String PASSWORD_RESET = "/user/resetpassword/";
}
