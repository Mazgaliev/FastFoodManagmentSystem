package com.example.fastfoodmanagmentbackend.Config;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

public class CustomUsernameAndPasswordAuthentication implements AuthenticationProvider {
    private final static String customToken = "shopId";

    private final FastFoodShopService fastFoodShopService;

    @Autowired
    private final HttpServletRequest httpServletRequest;

    public CustomUsernameAndPasswordAuthentication(FastFoodShopService fastFoodShopService, HttpServletRequest httpServletRequest) {
        this.fastFoodShopService = fastFoodShopService;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        FastFoodShopId shopId = FastFoodShopId.valueOf(httpServletRequest.getParameter(customToken));

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
