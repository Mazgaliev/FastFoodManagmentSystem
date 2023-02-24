package com.example.fastfoodmanagmentbackend.Config;

import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.Person;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.converter.Converter;
import com.example.fastfoodmanagmentbackend.Service.dto.FastFoodShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomUsernameAndPasswordAuthentication implements AuthenticationProvider {
    private final static String customToken = "shopId";

    private final FastFoodShopService fastFoodShopService;
    private final PasswordEncoder passwordEncoder;
    private final Converter converter;

    private final HttpServletRequest httpServletRequest;

    public CustomUsernameAndPasswordAuthentication(FastFoodShopService fastFoodShopService, PasswordEncoder passwordEncoder, Converter converter, HttpServletRequest httpServletRequest) {
        this.fastFoodShopService = fastFoodShopService;
        this.passwordEncoder = passwordEncoder;
        this.converter = converter;
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        FastFoodShopId shopId = FastFoodShopId.valueOf(httpServletRequest.getParameter(customToken));

        FastFoodShop shop = this.fastFoodShopService.findById(shopId);
        if (shop == null)
            throw new BadCredentialsException("Invalid shop id");
        if (username.equals("") || password.equals(""))
            throw new BadCredentialsException("Invalid Username or Password");

        Person worker = shop.findWorkerByUsername(username);

        if (!passwordEncoder.matches(password, worker.getPassword())) {
            throw new BadCredentialsException("Wrong password exception");
        }
        FastFoodShopDto dto = this.converter.convertToDto(shop, worker);
        return new UsernamePasswordAuthenticationToken(dto, worker.getPassword(), worker.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
