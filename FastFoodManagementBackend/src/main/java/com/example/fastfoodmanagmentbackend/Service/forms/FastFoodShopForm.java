package com.example.fastfoodmanagmentbackend.Service.forms;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.contact.Operator;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FastFoodShopForm {
    @NotNull
    String name;
    @NotNull
    String longitude;
    @NotNull
    String latitude;
    @NotNull
    String city;
    @NotNull
    String ownerName;
    @NotNull
    String ownerSurname;
    @NotNull
    String e_mail;
    @NotNull
    Operator ownerOperator;
    @NotNull
    String ownerPhone;

}
