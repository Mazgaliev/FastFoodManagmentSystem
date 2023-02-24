package com.example.fastfoodmanagmentbackend.Service.Implementation;

import com.example.fastfoodmanagmentbackend.Model.Enum.ItemType;
import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
import com.example.fastfoodmanagmentbackend.Model.Exceptions.*;
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
import com.example.fastfoodmanagmentbackend.Service.forms.FastFoodShopForm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class FastFoodShopServiceImplementation implements FastFoodShopService, UserDetailsService {
    private final FastFoodShopRepository fastFoodShopRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public FastFoodShop createShop(FastFoodShopForm form) throws PlaceNameAlreadyInUseException {
        FastFoodShop f = this.fastFoodShopRepository.findByNameLike(form.getName());
        if (f != null)
            throw new PlaceNameAlreadyInUseException();

        Location loc = Location.valueOf(form.getLongitude(), form.getLatitude(), form.getCity());
        PhoneNumber ownerPhone = PhoneNumber.valueOf(form.getOwnerOperator(), form.getOwnerPhone());
        Owner o = Owner.valueOf(form.getOwnerName(), form.getOwnerSurname(), form.getE_mail(), ownerPhone);
        FastFoodShop fastFoodShop = new FastFoodShop(form.getName(), loc, o);
        this.fastFoodShopRepository.saveAndFlush(fastFoodShop);

        Person worker = new Person(o.getOwnerSurname(), passwordEncoder.encode("dummyPassword"), Role.OWNER);
        FastFoodShop shop = this.fastFoodShopRepository.findById(fastFoodShop.getId()).orElseThrow(ShopWithIdDoesntExistException::new);
        shop.addWorker(worker);

        return this.fastFoodShopRepository.save(shop);
    }

    @Override
    public boolean deleteShop(FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        Set<Person> workers = shop.getWorkers();
        for (Person w : workers) {
            shop.removeWorker(w.getId());
        }
        this.fastFoodShopRepository.delete(shop);
        return true;
    }

    @Override
    public void addItem(String name, Currency currency, Double amount, ItemType type, FastFoodShopId shopId) throws ShopWithIdDoesntExistException {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        shop.addItem(new Item(name, Money.valueOf(currency, amount), type));
        this.fastFoodShopRepository.save(shop);
    }


    @Override
    public Item editItem(FastFoodShopId fastFoodShopId, Long id, String newName, Currency newCurrency, Double newAmount) throws ShopWithIdDoesntExistException {
        FastFoodShop shop = this.fastFoodShopRepository.findById(fastFoodShopId).orElseThrow(ShopWithIdDoesntExistException::new);
        Item i = shop.editItem(id, newName, newCurrency, newAmount);
        this.fastFoodShopRepository.saveAndFlush(shop);

        return i;
    }

    @Override
    public void deleteItem(Long id, FastFoodShopId shopId) throws ShopWithIdDoesntExistException {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        shop.removeItem(id);
    }

    @Override
    public Order createOrder(Currency currency, Double amount, List<Long> itemIds, FastFoodShopId shopId, String workerUsername) throws ShopWithIdDoesntExistException {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        Order o = shop.makeOrder(itemIds, Money.valueOf(currency, amount), workerUsername);
        this.fastFoodShopRepository.saveAndFlush(shop);
        return o;
    }


    @Override
    public Order editOrder(Long id, FastFoodShopId shopId, List<Long> itemIds, Currency currency, Double total, String workerUsername) throws ShopWithIdDoesntExistException, OrderDoesNotExistException, GenericException {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);

        Order o = shop.editOrder(id, Money.valueOf(currency, total), itemIds, workerUsername);

        this.fastFoodShopRepository.saveAndFlush(shop);
        return o;
    }

    @Override
    public Order deleteOrder(Long id, FastFoodShopId shopId) throws ShopWithIdDoesntExistException, InvalidOrderException {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        Order o = shop.removeOrder(id);
        if (o == null) {
            throw new InvalidOrderException("Order with id: " + id + " does not exist");
        }
        return o;
    }

    @Override
    public Set<Order> findAllOrders(FastFoodShopId shopId) throws ShopWithIdDoesntExistException {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        return shop.getOrders();
    }

    @Override
    public Set<Order> findAllOrdersBetween(LocalDateTime from, LocalDateTime to, FastFoodShopId shopId) throws ShopWithIdDoesntExistException {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        Set<Order> orders = shop.ordersBetweenDates(from, to);
        return orders;
    }

    @Override
    public void deleteFastFoodShop(FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        this.fastFoodShopRepository.delete(shop);
    }

    @Override
    public void createShopWorker(String username, String password, Role role, FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);
        Person p = new Person(username, this.passwordEncoder.encode(password), role);
        shop.addWorker(p);
        this.fastFoodShopRepository.saveAndFlush(shop);
    }

    @Override
    public void deleteShopWorker(WorkerId workerId, FastFoodShopId shopId) throws ShopWithIdDoesntExistException, PlaceMustHaveOwnerException {
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

    @Override
    public Set<Item> findAllItems(FastFoodShopId shopId) {
        FastFoodShop shop = this.fastFoodShopRepository.findById(shopId).orElseThrow(ShopWithIdDoesntExistException::new);

        return shop.getItems();
    }
}
