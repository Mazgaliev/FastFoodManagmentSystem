package com.example.fastfoodmanagmentbackend.Web;


import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/items"})
public class ItemController {

    private final FastFoodShopService fastFoodShopService;

    public ItemController(FastFoodShopService fastFoodShopService) {
        this.fastFoodShopService = fastFoodShopService;
    }
}
