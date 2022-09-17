package com.example.fastfoodmanagmentbackend.Service.forms;

import com.example.fastfoodmanagmentbackend.Model.Person;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Currency;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderForm {
    @NotNull
    Currency currency;
    @NotNull
    Double amount;
    @NotNull
    List<Long> itemIds;
    @NotNull
    FastFoodShopId shopId;
    @NotNull
    String workerUsername;
}
