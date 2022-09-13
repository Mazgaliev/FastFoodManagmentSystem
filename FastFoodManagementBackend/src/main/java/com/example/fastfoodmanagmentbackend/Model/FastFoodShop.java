package com.example.fastfoodmanagmentbackend.Model;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.Owner;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.contact.PhoneNumber;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.location.Location;
import com.example.fastfoodmanagmentbackend.Model.base.AbstractEntity;
import com.example.fastfoodmanagmentbackend.Model.base.DomainObjectId;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class FastFoodShop extends AbstractEntity<FastFoodShopId> {

    private String name;

    @Embedded
    private Location location;

    @Embedded
    private PhoneNumber phoneNumber;

    private String password;

    private String image;

    @Embedded
    private Owner owner;

    public FastFoodShop(String name, Location location, PhoneNumber phoneNumber, String password, String image, Owner owner) {
        super(DomainObjectId.randomId(FastFoodShopId.class));
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.image = image;
        this.owner = owner;
    }

    protected FastFoodShop() {

    }


    public void makeOrder() {

    }

    public void editOrder() {

    }

    public void removeOrder() {

    }
}
