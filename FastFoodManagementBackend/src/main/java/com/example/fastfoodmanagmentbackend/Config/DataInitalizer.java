package com.example.fastfoodmanagmentbackend.Config;

import com.example.fastfoodmanagmentbackend.Model.Enum.ItemType;
import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.contact.Operator;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Currency;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
public class DataInitalizer {

    private final FastFoodShopService fastFoodShopService;



    public DataInitalizer(FastFoodShopService fastFoodShopService) {
        this.fastFoodShopService = fastFoodShopService;
    }

//        @PostConstruct
    public void init() {
        FastFoodShop shop = this.fastFoodShopService.createShop("name", "123", "321", "Novo Selo", "Mite", "Mazgaliev", Operator.A1, "123456", "email");
        this.fastFoodShopService.addItem("itemName", Currency.MKD, 80.0, ItemType.FOOD, shop.getId());
        this.fastFoodShopService.createShopWorker("username", "password", Role.WORKER, shop.getId());
    }
}
