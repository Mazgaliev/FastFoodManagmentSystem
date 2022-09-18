package com.example.fastfoodmanagmentbackend.Model;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;
import lombok.Getter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Table(name = "order_")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDateTime orderTime;

    private LocalDateTime lastEditedTime;

    @Embedded
    private Money total;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Person worker;

    @ManyToMany
    List<Item> items;

    public Order(Money total, List<Item> items, Person worker) {
        this.orderTime = LocalDateTime.now();
        this.total = total;
        this.items = items;
        this.worker = worker;
        this.lastEditedTime = LocalDateTime.now();
    }


    public static Order buildOrder(Money total, List<Item> items, Person worker, LocalDateTime createdDate) {
        return new Order(total, items, worker, createdDate);
    }

    public Order() {

    }

    protected Order(Money total, List<Item> items, Person worker, LocalDateTime createdDate) {
        this.orderTime = createdDate;
        this.total = total;
        this.items = items;
        this.worker = worker;
        this.lastEditedTime = LocalDateTime.now();
    }

    public void modifyOrder(Money newTotal, List<Item> newItems) {

        this.total = newTotal;
        this.items = newItems;

        this.lastEditedTime = LocalDateTime.now();
    }

    public void removeWorker() {
        worker = null;
    }
}
