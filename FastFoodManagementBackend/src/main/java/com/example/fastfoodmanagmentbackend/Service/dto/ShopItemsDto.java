package com.example.fastfoodmanagmentbackend.Service.dto;

import com.example.fastfoodmanagmentbackend.Model.Item;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class ShopItemsDto {

    @NotNull
    Set<Item> foods;

    @NotNull
    Set<Item> drinks;

    @NotNull
    Set<Item> additives;
}
