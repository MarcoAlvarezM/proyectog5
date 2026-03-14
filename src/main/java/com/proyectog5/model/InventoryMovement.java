package com.proyectog5.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movements")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private String type; // ENTRADA o SALIDA

    @Column(name = "cantidad")
    private int quantity;

    @Column(name = "stock_anterior")
    private int previousStock;

    @Column(name = "stock_nuevo")
    private int newStock;

    @Column(name = "motivo")
    private String reason;

    @Column(name = "fecha")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public InventoryMovement() {
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPreviousStock() {
        return previousStock;
    }

    public int getNewStock() {
        return newStock;
    }

    public String getReason() {
        return reason;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPreviousStock(int previousStock) {
        this.previousStock = previousStock;
    }

    public void setNewStock(int newStock) {
        this.newStock = newStock;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
