package com.proyectog5.service;

import com.proyectog5.model.Product;
import com.proyectog5.model.Supplier;
import com.proyectog5.repository.ProductRepository;
import com.proyectog5.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private SupplierRepository supplierRepository;

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

    public Product updateProduct(Product product){
        return repository.save(product);
    }

    public void registerEntry(Long productId, int quantity){

        Product product = repository.findById(productId).orElse(null);

        if(product != null){
            product.setQuantity(product.getQuantity() + quantity);
            repository.save(product);
        }
    }

    public void registerExit(Long productId, int quantity){

        Product product = repository.findById(productId).orElse(null);

        if(product != null && product.getQuantity() >= quantity){
            product.setQuantity(product.getQuantity() - quantity);
            repository.save(product);
        }
    }

    public Supplier saveSupplier(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public List<Product> lowStock(){
        return repository.findAll()
                .stream()
                .filter(p -> p.getQuantity() < 5)
                .toList();
    }

    public List<Product> filterByCategory(String category){
        return repository.findByCategory(category);
    }
    
    public List<Product> searchProducts(String name){
    return repository.findByNameContaining(name);
}
}