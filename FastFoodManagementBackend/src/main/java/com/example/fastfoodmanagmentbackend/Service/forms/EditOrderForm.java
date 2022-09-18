package com.example.fastfoodmanagmentbackend.Service.forms;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Currency;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EditOrderForm {

    @NotNull
    Long id;

    @NotNull
    FastFoodShopId shopId;

    @NotNull
    List<Long> itemIds;

    @NotNull
    String workerUsername;

    @NotNull
    Currency currency;
    @NotNull
    Double amount;

}
