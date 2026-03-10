/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog5.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // ENTRY o EXIT
    private int quantity;

    private LocalDate date;

    @ManyToOne
    private Product product;

}
