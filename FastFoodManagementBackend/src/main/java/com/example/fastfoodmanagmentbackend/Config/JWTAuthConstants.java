package com.example.fastfoodmanagmentbackend.Config;

public class JWTAuthConstants {
    public static final long EXPIRATION_TIME = 64_000_000;
    public static final String SECRET = "SomeSecret";

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
}
