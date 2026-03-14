package com.proyectog5.controller;

import com.proyectog5.model.Product;
import com.proyectog5.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping("/products")
    public String listProducts(Model model){
        model.addAttribute("products", service.getAllProducts());
        return "products";
    }

    @GetMapping("/products/new")
    public String createProductForm(Model model){
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute Product product){
        service.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", service.getProduct(id));
        return "product_form";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        service.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping("/update")
    public String updateProduct(Product product){
        service.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/report")
    public String report(Model model){
        model.addAttribute("products", service.getAllProducts());
        return "report";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/low-stock")
public String lowStock(Model model){
    model.addAttribute("products", service.lowStock());
    return "products";
}

@PostMapping("/entry/{id}")
public String registerEntry(@PathVariable Long id, @RequestParam int quantity){
    service.registerEntry(id, quantity);
    return "redirect:/products";
}
@PostMapping("/exit/{id}")
public String registerExit(@PathVariable Long id, @RequestParam int quantity){
    service.registerExit(id, quantity);
    return "redirect:/products";
}

@GetMapping("/category/{category}")
public String filterByCategory(@PathVariable String category, Model model){
    model.addAttribute("products", service.filterByCategory(category));
    return "products";
}

@GetMapping("/search")
public String searchProducts(@RequestParam String name, Model model){

    model.addAttribute("products", service.searchProducts(name));

    return "products";
}
}
