package com.example.fastfoodmanagmentbackend.Repository;

import com.example.fastfoodmanagmentbackend.Model.ValueObjects.WorkerId;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fastfoodmanagmentbackend.Model.Person;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopWorkerRepository extends JpaRepository<Person, WorkerId> {
}
