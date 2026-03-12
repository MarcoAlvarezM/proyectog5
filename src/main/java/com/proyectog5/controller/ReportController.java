package com.proyectog5.controller;

import com.proyectog5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    @Autowired
    private ProductService productService;

    @GetMapping("/inventory-report")
    public String inventoryReport(Model model) {

        model.addAttribute("products", productService.getAllProducts());

        return "report";
    }
}
