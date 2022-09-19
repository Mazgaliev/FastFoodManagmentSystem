package com.example.fastfoodmanagmentbackend.Service.dto;

import com.example.fastfoodmanagmentbackend.Model.Item;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    @NotNull
    Long id;
    @NotNull
    LocalDateTime orderTime;

    @NotNull
    Money total;

    @NotNull
    WorkerDto worker;

    @NotNull
    List<Item> items;
}
