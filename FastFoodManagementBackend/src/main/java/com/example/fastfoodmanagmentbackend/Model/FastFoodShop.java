package com.example.fastfoodmanagmentbackend.Model;

import com.example.fastfoodmanagmentbackend.Model.Enum.Role;
import com.example.fastfoodmanagmentbackend.Model.Exceptions.*;
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
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Component
public class FastFoodShop extends AbstractEntity<FastFoodShopId> {

    private String name;

    @Embedded
    private Location location;

    @Embedded
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Item> items;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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
        Person worker = this.workers.stream().filter(w -> w.getUsername().equals(workerUsername)).findFirst().orElseThrow(() -> new GenericException("User does not exist with username " + workerUsername));
        List<Item> items = this.items.stream().filter(item -> itemIds.contains(item.getId())).toList();
        Order o = new Order(total, items, worker);
        this.orders.add(o);
        return o;
    }

    public Order editOrder(Long orderId, Money newTotal, List<Long> newItemIds, String workerUsername) {
        //TODO currency transitions
        Person worker = this.workers.stream().filter(w -> w.getUsername().equals(workerUsername)).findFirst().orElseThrow(() -> new GenericException("Worker does not exist in this shop"));
        List<Item> its = this.items.stream().filter(i -> newItemIds.contains(i.getId())).toList();
        Order o = this.orders.stream().filter(order -> order.getId().equals(orderId)).findFirst().orElseThrow(OrderDoesNotExistException::new);


        this.orders.remove(o);

        Order editedOrder = Order.buildOrder(newTotal, its, worker, o.getOrderTime());
        this.orders.add(editedOrder);

        return editedOrder;
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
        Item i = this.items.stream().filter(item -> item.getId().equals(itemId)).findFirst().orElseThrow(() -> new GenericException("This item does not exist"));

        for (Order o : this.orders) {
            o.getItems().removeIf(item -> item.getId().equals(itemId));
        }

        this.items.remove(i);
    }

    public Item editItem(Long itemId, String newName, Currency currency, Double amount) {
        Item i = this.items.stream().filter(item -> item.getId().equals(itemId)).findFirst().orElse(null);
        if (i == null) {
            throw new ItemDoesNotExistException();
        } else if (i.getPrice().getAmount() != amount) {
            Money money = Money.valueOf(currency, amount);
            i.changePrice(money);
        } else if (!i.getName().equals(name)) {
            i.changeName(newName);
        }
        return i;
    }

    public Person addWorker(Person person) {
//        var p = new Person(username, password, role);
        this.workers.add(person);
        return person;
    }

    public Person findWorkerByUsername(String username) {

        return this.workers.stream().filter(i -> i.getUsername().equals(username)).findFirst().orElseThrow(() -> new BadCredentialsException("Invalid worker username"));
    }

    public Person removeWorker(WorkerId workerId) {
        Person worker = this.workers.stream().filter(w -> w.getId().getId().equals(workerId.getId())).findFirst().orElseThrow(() -> new GenericException("Worker with that id does not exist"));
        Person owner = this.workers.stream().filter(w -> w.getRole().equals(Role.OWNER)).findFirst().orElseThrow(() -> new PlaceMustHaveOwnerException("Owner must exist"));
        Set<Order> newOrders = new HashSet<>();
        for (Order o : this.orders) {
            if (o.getWorker().getId().getId().equals(workerId.getId())) {
                List<Item> orderItems = o.getItems();
                List<Item> actualitems = this.items.stream().filter(orderItems::contains).toList();
                newOrders.add(Order.buildOrder(o.getTotal(), actualitems, owner, o.getOrderTime()));
            }
        }
        this.orders.addAll(newOrders);

        this.orders.removeIf(o -> o.getWorker().getId().getId().equals(worker.getId().getId()));

        this.workers.remove(worker);

        return worker;
    }

    public Set<Order> ordersBetweenDates(LocalDateTime start, LocalDateTime end) {

        return this.orders.parallelStream().filter(o -> {
            return (o.getOrderTime().equals(start) || o.getOrderTime().equals(end)) || (o.getOrderTime().isAfter(start)
                    && o.getOrderTime().isBefore(end));
        }).collect(Collectors.toUnmodifiableSet());

    }
}
