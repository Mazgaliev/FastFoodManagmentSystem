package com.example.fastfoodmanagmentbackend.Service.forms;

import com.example.fastfoodmanagmentbackend.Model.Enum.ItemType;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemForm {

    @NotNull
    String name;

    @NotNull
    ItemType itemType;

    @NotNull
    Money price;

    @NotNull
    FastFoodShopId shopId;

}
