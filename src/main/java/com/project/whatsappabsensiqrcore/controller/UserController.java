package com.project.whatsappabsensiqrcore.controller;

import com.project.whatsappabsensiqrcore.model.User;
import com.project.whatsappabsensiqrcore.model.UserRequest;
import com.project.whatsappabsensiqrcore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user.getKodeUnik(), user.getNomorWa());
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registrasi gagal: " + e.getMessage());
        }
    }
}

