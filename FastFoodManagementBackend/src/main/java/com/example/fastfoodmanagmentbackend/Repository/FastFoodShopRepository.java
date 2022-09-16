package com.example.fastfoodmanagmentbackend.Repository;

import com.example.fastfoodmanagmentbackend.Model.FastFoodShop;
import com.example.fastfoodmanagmentbackend.Model.ValueObjects.FastFoodShopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastFoodShopRepository extends JpaRepository<FastFoodShop, FastFoodShopId> {

    FastFoodShop findByNameLike(String name);
}
