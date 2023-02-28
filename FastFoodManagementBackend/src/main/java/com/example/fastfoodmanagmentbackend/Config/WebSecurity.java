package com.example.fastfoodmanagmentbackend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("stateful")
public class WebSecurity {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/login", "/api/register").permitAll()
                .antMatchers("/", "/api", "/api/home", "/api/delete").hasAnyAuthority("WORKER", "OWNER")
//                .antMatchers("/api/delete").hasRole("OWNER")
                .and()
                .formLogin()
                .loginPage("/api/login").permitAll()
                .defaultSuccessUrl("/api/home")
                .and()
                .logout()
                .logoutUrl("/api/logout").permitAll()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSSESIONID")
                .logoutSuccessUrl("/api/login");


        http.headers().frameOptions().sameOrigin();

        return http.build();
    }
}
