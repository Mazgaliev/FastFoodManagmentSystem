package com.example.fastfoodmanagmentbackend.Web;

import com.example.fastfoodmanagmentbackend.Model.Item;
import com.example.fastfoodmanagmentbackend.Model.Order;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.dto.OrderDto;
import com.example.fastfoodmanagmentbackend.Service.forms.ItemForm;
import com.example.fastfoodmanagmentbackend.Service.forms.OrderForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/order"})
public class OrderController {


    private final FastFoodShopService fastFoodShopService;

    public OrderController(FastFoodShopService fastFoodShopService) {
        this.fastFoodShopService = fastFoodShopService;
    }

    @PostMapping
    public OrderDto makeOrder(@RequestBody OrderForm form) {
        this.fastFoodShopService.createOrder(form.getCurrency(), form.getAmount(), form.getItemIds(), form.getShopId(), form.getWorkerUsername());

        return null;
    }
}
