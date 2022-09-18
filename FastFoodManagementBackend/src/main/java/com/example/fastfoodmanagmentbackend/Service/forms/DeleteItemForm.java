package com.example.fastfoodmanagmentbackend.Service.forms;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteItemForm {

    @NotNull
    FastFoodShopId shopId;

    @NotNull
    Long itemId;
}
