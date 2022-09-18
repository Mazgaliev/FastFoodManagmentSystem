package com.example.fastfoodmanagmentbackend.Service.forms;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ViewOrdersBetweenDateForm {

    @NotNull
    LocalDate start;
    @NotNull
    LocalDate end;
    @NotNull
    FastFoodShopId shopId;
}
