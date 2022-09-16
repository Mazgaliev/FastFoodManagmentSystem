package com.example.fastfoodmanagmentbackend.Service;

import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.Item;
import com.example.fastfoodmanagmentbackend.Model.Order;
import com.example.fastfoodmanagmentbackend.Model.Person;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.WorkerId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.contact.Operator;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.contact.PhoneNumber;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Currency;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.location.Location;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;

public interface FastFoodShopService {

    FastFoodShop createShop(String name, String longitude, String latitude, String city, String ownerName, String ownerSurname, Operator ownerOperator, String ownerPhoneNumber, String owner_email);

    void addItem(String name, Currency currency, Double amount, List<Long> itemids, FastFoodShopId shopId);

    void editItem(FastFoodShopId fastFoodShopId, Long id, String newName, Currency newCurrency, Double newAmount);

    void deleteItem(Long id, FastFoodShopId shopId);

    Order createOrder(Currency currency, Double amount, List<Long> itemIds, FastFoodShopId shopId, Person worker, String workerUsername);

    Order editOrder(Long id, FastFoodShopId shopId, List<Long> itemIds, Currency currency, Double total);

    Order deleteOrder(Long id, FastFoodShopId shopId);

    List<Order> findAllOrders(FastFoodShopId shopId);

    List<Order> findAllOrdersBetween(LocalDateTime from, LocalDateTime to, FastFoodShopId shopId);

    void deleteFastFoodShop(FastFoodShopId shopId);

    void createShopWorker(String username, String password, FastFoodShopId shopId);

    void deleteShopWorker(WorkerId workerId, FastFoodShopId shopId);

    UserDetails loadUserByUsernameAndShop(String username, FastFoodShopId fastFoodShopId);

}
