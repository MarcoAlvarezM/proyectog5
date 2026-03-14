/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.proyectog5.controller;

import com.proyectog5.model.User;
import com.proyectog5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.proyectog5.model.Role;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String listarUsuarios(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "users";
    }

    //  Mostrar formulario para crear usuario
    @GetMapping("/new")
    public String nuevoUsuario(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

   
   @PostMapping("/save")
public String guardarUsuario(User user, @RequestParam Integer roleId) {
    // Codificar contraseña
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setActive(true);
    
    // ROL
    Role role = new Role();
    role.setId(roleId);
    
    // Asignar nombre según el ID
    if(roleId == 1) role.setName("ADMIN");
    else if(roleId == 2) role.setName("EMPLOYEE");
    else role.setName("USER");
    
    Set<Role> roles = new HashSet<>();
    roles.add(role);
    user.setRoles(roles);
    
    userRepo.save(user);
    return "redirect:/users";
}

    @GetMapping("/block/{id}")
    public String bloquear(@PathVariable Integer id){
        User u = userRepo.findById(id).orElseThrow();
        u.setActive(false);
        userRepo.save(u);
        return "redirect:/users";
    }

    @GetMapping("/unblock/{id}")
    public String desbloquear(@PathVariable Integer id){
        User u = userRepo.findById(id).orElseThrow();
        u.setActive(true);
        userRepo.save(u);
        return "redirect:/users";
    }
    
    
    @GetMapping("/edit/{id}")
    public String editarUsuario(@PathVariable Integer id, Model model) {
        User user = userRepo.findById(id).orElseThrow();
        model.addAttribute("user", user);
        return "user_form";
    }
    

    @GetMapping("/delete/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }
}