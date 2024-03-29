package com.example.fastfoodmanagmentbackend.Service;

import com.example.fastfoodmanagmentbackend.Model.Enum.ItemType;
import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
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
import com.example.fastfoodmanagmentbackend.Service.dto.RegisterMailDto;
import com.example.fastfoodmanagmentbackend.Service.dto.WorkerDto;
import com.example.fastfoodmanagmentbackend.Service.forms.FastFoodShopForm;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface FastFoodShopService {

    RegisterMailDto createShop(FastFoodShopForm form);
    FastFoodShop deleteShop(FastFoodShopId shopId);

    void addItem(String name, Currency currency, Double amount, ItemType type, FastFoodShopId shopId);

    Item editItem(FastFoodShopId fastFoodShopId, Long id, String newName, Currency newCurrency, Double newAmount);

    void deleteItem(Long id, FastFoodShopId shopId);

    Order createOrder(Currency currency, Double amount, List<Long> itemIds, FastFoodShopId shopId, String workerUsername);

    Order editOrder(Long id, FastFoodShopId shopId, List<Long> itemIds, Currency currency, Double total, String workerUsername);

    Order deleteOrder(Long id, FastFoodShopId shopId);

    Set<Order> findAllOrders(FastFoodShopId shopId);

    Set<Order> findAllOrdersBetween(LocalDateTime from, LocalDateTime to, FastFoodShopId shopId);


    FastFoodShop createShopWorker(String username, String password, Role role, FastFoodShopId shopId);

    FastFoodShop deleteShopWorker(WorkerId workerId, FastFoodShopId shopId);

    UserDetails loadUserByUsernameAndShop(String username, FastFoodShopId fastFoodShopId);

    FastFoodShop findById(FastFoodShopId shopId);

    Set<Item> findAllItems(FastFoodShopId shopId);

    Set<Person> findAllWorkers(FastFoodShopId shopId);

}
