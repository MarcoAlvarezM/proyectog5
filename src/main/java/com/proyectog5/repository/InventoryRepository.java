package com.proyectog5.repository;

import com.proyectog5.model.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryMovement, Long> {

    
    
}