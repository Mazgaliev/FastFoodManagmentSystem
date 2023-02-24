package com.example.fastfoodmanagmentbackend.Service.dto;

import com.example.fastfoodmanagmentbackend.Model.Item;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.Owner;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.location.Location;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class FastFoodShopDto {


    @NotNull
    FastFoodShopId id;

    @NotNull
    String name;

    @NotNull
    Location location;

    @NotNull
    Owner owner;

    @NotNull
    WorkerDto currentWorker;

    Set<Item> foods;

    Set<Item> drinks;

    Set<Item> additives;

}
