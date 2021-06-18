package edu.ap.group10.leapwebapp.security;

public class SecurityConstraints {

    private SecurityConstraints() {
    }

    public static final String SECRET = "(s_th15,32!ch@r4ct3rs?lo>ng or<no@ma.be!@We#ex73nd t4.s*7o{64^?";
    public static final String USERID_SECRET = ",A32_Characters-Long_String_Yepp";
    public static final String APPLICATION_ADMIN_SECRET = "@ppl1c@t10n_@7m1n";
    public static final long EXPIRATION_TIME = 900_000;
    public static final long EXPIRATION_TIME_PASSWORDRESET = 600 * 600 * 10l;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public static final String SIGN_UP_URL = "/user";
    public static final String SIGN_IN_URL = "/user/login";
    public static final String COMPANY_SIGN_UP = "/company";
    public static final String COMPANY_APPLICATION = "/companies/{role}";
    public static final String USER_ADMIN_SIGN_UP = "/useradmin";
    public static final String PASSWORD_RESET = "/user/resetpassword/";
    public static final String APPLICATION_ADMIN = "/application-admin";
    public static final String ACTUATOR = "/actuator/**";
}
