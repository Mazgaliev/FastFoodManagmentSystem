package com.example.fastfoodmanagmentbackend.Service.dto;


import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterMailDto {

    @NotNull
    FastFoodShopId shopId;
    @NotNull
    String password;
    @NotNull
    String username;
    @NotNull
    String email;

}
