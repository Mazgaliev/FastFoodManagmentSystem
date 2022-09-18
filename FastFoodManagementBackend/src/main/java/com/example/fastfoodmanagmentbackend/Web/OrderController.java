package com.example.fastfoodmanagmentbackend.Web;

import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.converter.Converter;
import com.example.fastfoodmanagmentbackend.Service.dto.OrderDto;
import com.example.fastfoodmanagmentbackend.Service.forms.DeleteOrderForm;
import com.example.fastfoodmanagmentbackend.Service.forms.EditOrderForm;
import com.example.fastfoodmanagmentbackend.Service.forms.OrderForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/api/order"})
public class OrderController {


    private final FastFoodShopService fastFoodShopService;
    private final Converter converter;

    public OrderController(FastFoodShopService fastFoodShopService, Converter converter) {
        this.fastFoodShopService = fastFoodShopService;
        this.converter = converter;
    }

    @PostMapping
    public OrderDto makeOrder(@RequestBody OrderForm form) {
        OrderDto dto = this.converter
                .convertToDto(this.fastFoodShopService
                        .createOrder(form.getCurrency(), form.getAmount(), form.getItemIds(), form.getShopId(), form.getWorkerUsername()));

        return dto;
    }

    @PostMapping("/remove")
    public void deleteOrder(@RequestBody DeleteOrderForm form) {

        this.fastFoodShopService.deleteOrder(form.getOrderId(), form.getFastFoodShopId());

    }

    @PostMapping("/edit")
    public OrderDto editOrder(@RequestBody EditOrderForm form) {
        OrderDto dto = this.converter
                .convertToDto(this.fastFoodShopService
                        .editOrder(form.getId(), form.getShopId(), form.getItemIds(), form.getCurrency(), form.getAmount(), form.getWorkerUsername()));
        return dto;
    }
}
