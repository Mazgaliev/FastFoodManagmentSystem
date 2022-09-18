package com.example.fastfoodmanagmentbackend.Service.forms;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.WorkerId;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteWorkerForm {

    @NotNull
    WorkerId workerId;

    @NotNull
    FastFoodShopId shopId;
}
