package com.example.fastfoodmanagmentbackend.Model.ValueObjects;

import com.example.fastfoodmanagmentbackend.Model.base.DomainObjectId;

public class FastFoodShopId extends DomainObjectId {

    protected FastFoodShopId(String uuid) {
        super(uuid);
    }

    public static FastFoodShopId valueOf(String uuid) {
        return new FastFoodShopId(uuid);
    }

    private FastFoodShopId() {
        super(FastFoodShopId.randomId(FastFoodShopId.class).getId());
    }
}
