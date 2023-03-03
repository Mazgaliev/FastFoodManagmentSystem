package com.example.fastfoodmanagmentbackend.Config.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.fastfoodmanagmentbackend.Config.JWTAuthConstants;
import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.Person;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.converter.Converter;
import com.example.fastfoodmanagmentbackend.Service.dto.FastFoodShopDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final static String customToken = "shopId";

    private final AuthenticationManager authenticationManager;
    private final FastFoodShopService fastFoodShopService;
    private final PasswordEncoder passwordEncoder;
    private final Converter converter;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, FastFoodShopService fastFoodShopService, PasswordEncoder passwordEncoder, Converter converter) {
        this.authenticationManager = authenticationManager;
        this.fastFoodShopService = fastFoodShopService;
        this.passwordEncoder = passwordEncoder;
        this.converter = converter;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        FastFoodShopId shopId = FastFoodShopId.valueOf(request.getParameter(customToken));
        FastFoodShop shop = this.fastFoodShopService.findById(shopId);

        if (shop == null)
            throw new BadCredentialsException("Invalid shop id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || username.isEmpty()) {
            throw new BadCredentialsException("No username provided");
        }

        Person worker = shop.findWorkerByUsername(username);

        if (!passwordEncoder.matches(password, worker.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        FastFoodShopDto dto = this.converter.convertToDto(shop, worker);
        return new UsernamePasswordAuthenticationToken(dto, password, worker.getAuthorities());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
        FastFoodShopDto userDetails = (FastFoodShopDto) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(new ObjectMapper()
                        .writeValueAsString(userDetails))
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTAuthConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JWTAuthConstants.SECRET));

        response.addHeader(JWTAuthConstants.HEADER_STRING, JWTAuthConstants.TOKEN_PREFIX + token);
        response.getWriter().append(token);
    }
}
