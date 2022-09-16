package com.example.fastfoodmanagmentbackend.Model;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.financial.Money;
import lombok.Getter;

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

    @Embedded
    private Money total;

    @ManyToOne

    private Person worker;

    @ManyToMany
    List<Item> items;

    public Order(Money total, List<Item> items, Person worker) {
        this.orderTime = LocalDateTime.now();
        this.total = total;
        this.items = items;
    }

    public Order() {

    }
}
