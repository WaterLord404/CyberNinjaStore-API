package com.cyberninja.security.common;

public class SecurityConstants {

	// Secret key must be over 256 bits for HMAC_512, otherwise it will fail
	// Better to be set at application.properties
    public static final String 	SECRET 					= "YourKeyToGenJWT_isUp2U_@_YourKeyToGenJWT_isUp2U_@_YourKeyToGenJWT_isUp2U";
    
    public static final long 	EXPIRATION_TIME 		= 604_000_000; 
    public static final String 	TOKEN_PREFIX 			= "Bearer ";
    public static final String 	HEADER_STRING 			= "Authorization";
    public static final String 	SIGN_UP_URL 			= "/user/sign-up";
    public static final String 	LOG_IN 					= "/user/login";
    
    // Google
 	public static final String GOOGLE_CLIENTID = "899814992698-rfm638ehb7fb39t7t52c6j44du35fg8h.apps.googleusercontent.com";
}
