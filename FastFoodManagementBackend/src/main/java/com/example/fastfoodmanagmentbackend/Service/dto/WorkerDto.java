package com.example.fastfoodmanagmentbackend.Service.dto;

import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {

    @NotNull
    String username;

    @NotNull
    Role role;
}
