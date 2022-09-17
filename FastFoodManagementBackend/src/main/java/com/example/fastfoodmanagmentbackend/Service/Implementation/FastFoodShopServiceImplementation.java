package com.example.fastfoodmanagmentbackend.Service.Implementation;

import com.example.fastfoodmanagmentbackend.Model.Enum.ItemType;
import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
import com.example.fastfoodmanagmentbackend.Model.Exceptions.PlaceNameAlreadyInUseException;
import com.example.fastfoodmanagmentbackend.Model.Exceptions.ShopWithIdDoesntExistException;
import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.Item;
import com.example.fastfoodmanagmentbackend.Model.Order;
import com.example.fastfoodmanagmentbackend.Model.Person;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.Owner;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.WorkerId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.contact.Operator;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.contact.PhoneNumber;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Currency;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.location.Location;
import com.example.fastfoodmanagmentbackend.Repository.FastFoodShopRepository;
import com.example.fastfoodmanagmentbackend.Service.FastFoodShopService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class FastFoodShopServiceImplementation implements FastFoodShopService, UserDetailsService {
    private final FastFoodShopRepository fastFoodShopRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public FastFoodShop createShop(String name, String longitude, String latitude, String city, String ownerName, String ownerSurname, Operator ownerOperator, String ownerPhoneNumber, String owner_email) {
        FastFoodShop f = this.fastFoodShopRepository.findByNameLike(name);
        if (f != null)
            throw new PlaceNameAlreadyInUseException();

        Location loc = Location.valueOf(longitude, latitude, city);
        PhoneNumber ownerPhone = PhoneNumber.valueOf(ownerOperator, ownerPhoneNumber);
        Owner o = Owner.valueOf(ownerName, ownerSurname, owner_email, ownerPhone);
        FastFoodShop fastFoodShop = new FastFoodShop(name, loc, o);


        return this.fastFoodShopRepository.saveAndFlush(fastFoodShop);
    }

    @Override
    public void addItem(String name, Currency currency, Double amount, ItemType type, FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        shop.addItem(new Item(name, Money.valueOf(currency, amount), type));
        this.fastFoodShopRepository.save(shop);
    }


    @Override
    public void editItem(FastFoodShopId fastFoodShopId, Long id, String newName, Currency newCurrency, Double newAmount) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(fastFoodShopId).orElseThrow(ShopWithIdDoesntExistException::new);
        shop.editItem(id, newName, newCurrency, newAmount);
        this.fastFoodShopRepository.saveAndFlush(shop);
    }

    @Override
    public void deleteItem(Long id, FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        shop.removeItem(id);
    }

    @Override
    public Order createOrder(Currency currency, Double amount, List<Long> itemIds, FastFoodShopId shopId, String workerUsername) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        shop.makeOrder(itemIds, Money.valueOf(currency, amount), workerUsername);
        this.fastFoodShopRepository.saveAndFlush(shop);
        return null;
    }


    @Override
    public Order editOrder(Long id, FastFoodShopId shopId, List<Long> itemIds, Currency currency, Double total) {
        return null;
    }

    @Override
    public Order deleteOrder(Long id, FastFoodShopId shopId) {
        return null;
    }

    @Override
    public List<Order> findAllOrders(FastFoodShopId shopId) {
        return null;
    }

    @Override
    public List<Order> findAllOrdersBetween(LocalDateTime from, LocalDateTime to, FastFoodShopId shopId) {
        return null;
    }

    @Override
    public void deleteFastFoodShop(FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        this.fastFoodShopRepository.delete(shop);
    }

    @Override
    public void createShopWorker(String username, String password, Role role, FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        shop.addWorker(username, this.passwordEncoder.encode(password), role);
        this.fastFoodShopRepository.saveAndFlush(shop);
    }

    @Override
    public void deleteShopWorker(WorkerId workerId, FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        shop.removeWorker(workerId);
        this.fastFoodShopRepository.saveAndFlush(shop);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByUsernameAndShop(String username, FastFoodShopId fastFoodShopId) throws UsernameNotFoundException {
        if (username == null)
            throw new UsernameNotFoundException("Invalid username");
        if (fastFoodShopId == null)
            throw new ShopWithIdDoesntExistException();
        FastFoodShop shop = this.fastFoodShopRepository.findById(fastFoodShopId).orElseThrow(ShopWithIdDoesntExistException::new);

        UserDetails p = shop.getWorkers().stream().filter(f -> f.getUsername().equals(username)).findFirst().orElseThrow(() -> new UsernameNotFoundException("Invalid username provided"));

        return p;
    }

    @Override
    public FastFoodShop findById(FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);

        return shop;
    }
}
