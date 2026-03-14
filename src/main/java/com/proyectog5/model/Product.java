package com.proyectog5.model;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "codigo")
    private String code;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "categoria")
    private String category;

    @Column(name = "precio")
    private double price;

    @Column(name = "stock")
    private int quantity;

    @Column(name = "stock_minimo")
    private int minStock;

    public Product() {
    }

    public Product(String name, String code, String description, String category, double price, int quantity, int minStock) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.minStock = minStock;
    }

    // getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

}
