package com.proyectog5.controller;

import com.proyectog5.repository.ProductRepository;
import com.proyectog5.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        List<Product> products = productRepository.findAll();

        model.addAttribute("products", products);
        model.addAttribute("totalProducts", products.size());

        return "dashboard";
    }
}