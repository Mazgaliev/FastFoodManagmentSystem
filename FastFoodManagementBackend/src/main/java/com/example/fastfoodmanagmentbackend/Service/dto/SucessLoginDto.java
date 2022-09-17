package com.example.fastfoodmanagmentbackend.Service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SucessLoginDto {

    @NotNull
    FastFoodShopDto fastFoodShopDto;

    @NotNull
    WorkerDto workerDto;

}
