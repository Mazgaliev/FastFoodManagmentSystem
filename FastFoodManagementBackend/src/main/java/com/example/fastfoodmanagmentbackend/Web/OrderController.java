package com.example.fastfoodmanagmentbackend.Web;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import com.example.fastfoodmanagmentbackend.Service.converter.Converter;
import com.example.fastfoodmanagmentbackend.Service.dto.OrderDto;
import com.example.fastfoodmanagmentbackend.Service.forms.DeleteOrderForm;
import com.example.fastfoodmanagmentbackend.Service.forms.EditOrderForm;
import com.example.fastfoodmanagmentbackend.Service.forms.OrderForm;
import com.example.fastfoodmanagmentbackend.Service.forms.ViewOrdersBetweenDateForm;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin("*")
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

    @DeleteMapping("/remove")
    public void deleteOrder(@RequestBody DeleteOrderForm form) {

        this.fastFoodShopService.deleteOrder(form.getOrderId(), form.getShopId());

    }

    @PostMapping("/edit")
    public OrderDto editOrder(@RequestBody EditOrderForm form) {
        OrderDto dto = this.converter
                .convertToDto(this.fastFoodShopService
                        .editOrder(form.getId(), form.getShopId(), form.getItemIds(), form.getCurrency(), form.getAmount(), form.getWorkerUsername()));
        return dto;
    }

    @PostMapping("/view/between")
    public Set<OrderDto> viewOrders(@RequestBody ViewOrdersBetweenDateForm form) {
        Set<OrderDto> orders = this.converter
                .convertToDto(this.fastFoodShopService.findAllOrdersBetween(form.getStart().atStartOfDay(), form.getEnd().plusDays(1).atStartOfDay(), form.getShopId()));
        return orders;
    }

    @GetMapping("/{shopId}")
    public Set<OrderDto> viewAllOrders(@PathVariable FastFoodShopId shopId) {
        return this.converter.convertToDto(this.fastFoodShopService.findAllOrders(shopId));
    }
}
