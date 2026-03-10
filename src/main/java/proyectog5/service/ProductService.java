package com.proyectog5.service;

import com.proyectog5.model.Product;
import com.proyectog5.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public Product saveProduct(Product product){
        return repository.save(product);
    }

    public Product getProduct(Long id){
        return repository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }
}