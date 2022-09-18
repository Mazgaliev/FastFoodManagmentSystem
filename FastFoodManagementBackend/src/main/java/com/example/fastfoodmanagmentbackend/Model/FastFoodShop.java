package com.example.fastfoodmanagmentbackend.Model;

import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
import com.example.fastfoodmanagmentbackend.Model.Exceptions.GenericException;
import com.example.fastfoodmanagmentbackend.Model.Exceptions.ItemDoesNotExistException;
import com.example.fastfoodmanagmentbackend.Model.Exceptions.ItemNameLikeException;
import com.example.fastfoodmanagmentbackend.Model.Exceptions.PlaceMustHaveOwnerException;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.Owner;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.WorkerId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Currency;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.location.Location;
import com.example.fastfoodmanagmentbackend.Model.base.AbstractEntity;
import com.example.fastfoodmanagmentbackend.Model.base.DomainObjectId;
import lombok.Getter;
import org.springframework.security.authentication.BadCredentialsException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
public class FastFoodShop extends AbstractEntity<FastFoodShopId> {

    private String name;

    @Embedded
    private Location location;

    @Embedded
    private Owner owner;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Item> items;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Person> workers;

    public FastFoodShop(String name, Location location, Owner owner) {
        super(DomainObjectId.randomId(FastFoodShopId.class));
        this.name = name;
        this.location = location;
        this.owner = owner;
        this.items = Set.of();
        this.workers = Set.of();
        this.orders = Set.of();
    }

    protected FastFoodShop() {
        super(FastFoodShopId.randomId(FastFoodShopId.class));
    }


    public Order makeOrder(List<Long> itemIds, Money total, String workerUsername) {
        Person worker = this.workers.stream().filter(w -> w.getUsername().equals(workerUsername)).findFirst().orElseThrow(null);
        List<Item> items = this.items.stream().filter(item -> itemIds.contains(item.getId())).toList();
        Order o = new Order(total, items, worker);
        this.orders.add(o);
        return o;
    }

    public Order editOrder(Long orderId, Money newTotal, List<Long> newItemIds, String workerUsername) {

        this.orders.removeIf(o -> o.getId().equals(orderId));
        Order o = makeOrder(newItemIds, newTotal, workerUsername);

        return o;
    }

    public Order removeOrder(Long orderId) {
        Order order = null;
        for (Order o : this.orders) {
            if (o.getId().equals(orderId)) {
                order = o;
                this.orders.remove(o);
            }
        }
        return order;
    }

    public void addItem(Item item) {
        int c = 0;
        for (Item i : items) {
            if (i.getName().equals(item.getName())) {
                c++;
            }
        }
        if (c != 0) {
            throw new ItemNameLikeException();
        } else {
            this.items.add(item);
        }
    }

    public void addItems(List<Item> items) {
        this.items.addAll(items);
    }

    public void removeItem(Long itemId) {
        this.items.removeIf(item -> item.getId().equals(itemId));
    }

    public void editItem(Long itemId, String newName, Currency currency, Double amount) {
        Item i = this.items.stream().filter(item -> item.getId().equals(itemId)).findFirst().orElse(null);
        if (i == null) {
            throw new ItemDoesNotExistException();
        } else if (newName.equals(name)) {
            Money money = Money.valueOf(currency, amount);
            i.changePrice(money);
        } else {
            i.changeName(newName);
        }
    }

    public Person addWorker(String username, String password, Role role) {
        var p = new Person(username, password, role);
        this.workers.add(p);
        return p;
    }

    public Person findWorkerByUsername(String username) {
        return this.workers.stream().filter(i -> i.getUsername().equals(username)).findFirst().orElseThrow(() -> new BadCredentialsException("Invalid worker username"));
    }

    public void removeWorker(WorkerId workerId) {
        Person worker = this.workers.stream().filter(w -> w.getId().getId().equals(workerId.getId())).findFirst().orElseThrow(() -> new GenericException("Worker with that id does not exist"));
        Person owner = this.workers.stream().filter(w -> w.getRole().equals(Role.OWNER)).findFirst().orElseThrow(() -> new PlaceMustHaveOwnerException("Owner must exist"));

        this.orders
                .addAll(this.orders.stream().filter(o -> o.getWorker().getId().getId().equals(workerId.getId()))
                .map(w -> Order.buildOrder(w.getTotal(), w.getItems(), owner, w.getOrderTime()))
                        .collect(Collectors.toSet()));

        this.orders.removeIf(o -> o.getWorker().getId().getId().equals(worker.getId().getId()));
        this.workers.remove(worker);
    }

    public Set<Order> transferOrders(Set<Order> orders) {
        this.orders.addAll(orders);
        return orders;
    }

    public Set<Order> ordersBetweenDates(LocalDateTime start, LocalDateTime end) {
        return this.orders.parallelStream().filter(o -> o.getOrderTime().equals(start) || o.getOrderTime().equals(end)).filter(o -> o.getOrderTime().isAfter(start) && o.getOrderTime().isBefore(end)).collect(Collectors.toSet());


    }
}
