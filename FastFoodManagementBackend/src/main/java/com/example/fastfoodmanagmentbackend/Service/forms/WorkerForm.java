package com.example.fastfoodmanagmentbackend.Service.forms;

import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerForm {

    @NotNull
    String username;
    @NotNull
    String password;
    @NotNull
    Role role;
    @NotNull
    FastFoodShopId fastFoodShopId;
}
