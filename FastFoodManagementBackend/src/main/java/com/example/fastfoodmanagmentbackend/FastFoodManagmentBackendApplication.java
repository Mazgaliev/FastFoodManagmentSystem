package com.example.fastfoodmanagmentbackend;

import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class FastFoodManagmentBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastFoodManagmentBackendApplication.class, args);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }


}
