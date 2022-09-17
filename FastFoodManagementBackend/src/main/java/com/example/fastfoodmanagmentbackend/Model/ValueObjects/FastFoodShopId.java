package com.example.fastfoodmanagmentbackend.Model.ValueObjects;

import com.example.fastfoodmanagmentbackend.Model.base.DomainObjectId;

import javax.persistence.Embeddable;

public class FastFoodShopId extends DomainObjectId {

    public FastFoodShopId(String uuid) {
        super(uuid);
    }

    public static FastFoodShopId valueOf(String uuid) {
        return new FastFoodShopId(uuid);
    }

    private FastFoodShopId() {
        super(FastFoodShopId.randomId(FastFoodShopId.class).getId());
    }
}
