/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
/**
 *
 * @author ADM
 */
@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Authentication auth) {
        boolean isUser = auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));

        if(isUser){
            return "home_user";
        }
        return "home";
    }


@GetMapping("/catalogo")
public String catalogo() {
    return "home_user"; 
}
}