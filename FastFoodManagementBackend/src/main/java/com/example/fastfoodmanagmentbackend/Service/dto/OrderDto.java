package com.example.fastfoodmanagmentbackend.Service.dto;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class OrderDto {

    @NotNull
    private LocalDateTime orderTime;

    @NotNull
    private Money total;

    @NotNull
    private WorkerDto worker;
}
