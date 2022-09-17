package com.example.fastfoodmanagmentbackend.Service.converter;

import com.example.fastfoodmanagmentbackend.Model.Enum.ItemType;
import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.Item;
import com.example.fastfoodmanagmentbackend.Model.Order;
import com.example.fastfoodmanagmentbackend.Model.Person;
import com.example.fastfoodmanagmentbackend.Service.dto.FastFoodShopDto;
import com.example.fastfoodmanagmentbackend.Service.dto.OrderDto;
import com.example.fastfoodmanagmentbackend.Service.dto.SucessLoginDto;
import com.example.fastfoodmanagmentbackend.Service.dto.WorkerDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Converter {


    public FastFoodShopDto convertToDto(FastFoodShop shop, Person worker) {
        FastFoodShopDto dto = new FastFoodShopDto();

        dto.setId(shop.getId());
        dto.setName(shop.getName());
        dto.setOwner(shop.getOwner());
        dto.setFood(shop.getItems());
        dto.setCurrentWorker(convertToDto(worker));

        List<Item> allItems = shop.getItems();
        List<Item> foods = new ArrayList<>();
        List<Item> drinks = new ArrayList<>();
        List<Item> additives = new ArrayList<>();
        for (Item i : allItems) {
            if (i.getType().equals(ItemType.FOOD)) {
                foods.add(i);
            } else if (i.getType().equals(ItemType.ADDITIVE)) {
                additives.add(i);
            } else {
                drinks.add(i);
            }
        }
        dto.setFood(foods);
        dto.setDrinks(drinks);
        dto.setAdditives(additives);

        return dto;
    }

    public WorkerDto convertToDto(Person person) {
        WorkerDto dto = new WorkerDto();
        dto.setUsername(person.getUsername());
        dto.setRole(person.getRole());
        return dto;
    }


    public OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setOrderTime(order.getOrderTime());
        dto.setWorker(convertToDto(order.getWorker()));
        dto.setTotal(order.getTotal());

        return dto;
    }
}
