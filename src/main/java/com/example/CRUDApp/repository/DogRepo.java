package com.example.CRUDApp.repository;

import com.example.CRUDApp.entity.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepo extends JpaRepository<Dog, Long> {
}
