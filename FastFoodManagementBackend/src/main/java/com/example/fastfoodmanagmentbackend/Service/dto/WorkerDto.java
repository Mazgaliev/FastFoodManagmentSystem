package com.example.fastfoodmanagmentbackend.Service.dto;

import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.WorkerId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDto {

    @NotNull
    WorkerId id;
    @NotNull
    String username;

    @NotNull
    Role role;
}
