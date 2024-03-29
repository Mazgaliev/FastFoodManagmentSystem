package com.example.fastfoodmanagmentbackend.Service.converter;

import com.example.fastfoodmanagmentbackend.Model.Enum.ItemType;
import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.Item;
import com.example.fastfoodmanagmentbackend.Model.Order;
import com.example.fastfoodmanagmentbackend.Model.Person;
import com.example.fastfoodmanagmentbackend.Service.dto.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Converter {

    public ShopItemsDto convertToDto(FastFoodShop shop) {
        ShopItemsDto dto = new ShopItemsDto();
        Set<Item> allItems = shop.getItems();
        Set<Item> foods = new HashSet<>();
        Set<Item> drinks = new HashSet<>();
        Set<Item> additives = new HashSet<>();
        for (Item i : allItems) {
            if (i.getType().toString().equals(ItemType.FOOD.toString())) {
                foods.add(i);
            } else if (i.getType().toString().equals(ItemType.ADDITIVE.toString())) {
                additives.add(i);
            } else {
                drinks.add(i);
            }
        }
        dto.setFoods(foods);
        dto.setDrinks(drinks);
        dto.setAdditives(additives);
        return dto;

    }

    public FastFoodShopDto convertToDto(FastFoodShop shop, Person worker) {
        FastFoodShopDto dto = new FastFoodShopDto();

        dto.setId(shop.getId());
        dto.setName(shop.getName());
        dto.setOwner(shop.getOwner());
        dto.setFoods(shop.getItems());
        dto.setCurrentWorker(convertToDto(worker));

        Set<Item> allItems = shop.getItems();
        Set<Item> foods = new HashSet<>();
        Set<Item> drinks = new HashSet<>();
        Set<Item> additives = new HashSet<>();
        for (Item i : allItems) {
            if (i.getType().toString().equals(ItemType.FOOD.toString())) {
                foods.add(i);
            } else if (i.getType().toString().equals(ItemType.ADDITIVE.toString())) {
                additives.add(i);
            } else {
                drinks.add(i);
            }
        }
        dto.setFoods(foods);
        dto.setDrinks(drinks);
        dto.setAdditives(additives);

        return dto;
    }

    public WorkerDto convertToDto(Person person) {
        WorkerDto dto = new WorkerDto();
        dto.setId(person.getId());
        dto.setUsername(person.getUsername());
        dto.setRole(person.getRole());
        return dto;
    }


    public OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setOrderTime(order.getOrderTime());
        dto.setWorker(convertToDto(order.getWorker()));
        dto.setTotal(order.getTotal());
        dto.setItems(order.getItems());
        dto.setId(order.getId());

        return dto;
    }

    public Set<WorkerDto> convertToWorkerDto(Set<Person> workers) {

        return workers.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    public Set<OrderDto> convertToDto(Set<Order> orders) {

        return orders.stream().map(this::convertToDto).collect(Collectors.toSet());
    }


}
