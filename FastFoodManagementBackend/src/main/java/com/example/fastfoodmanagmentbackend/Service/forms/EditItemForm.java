package com.example.fastfoodmanagmentbackend.Service.forms;

import com.example.fastfoodmanagmentbackend.Model.Enum.ItemType;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Currency;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class EditItemForm {
    @NotNull
    Long itemId;

    @NotNull
    FastFoodShopId shopId;

    @NotNull
    String itemName;

    @NotNull
    Currency currency;

    @NotNull
    Double amount;

    @NotNull ItemType type;

}
