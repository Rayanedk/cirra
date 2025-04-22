package com.example.test.controller;

import com.example.test.security.JwtUtils;
import com.example.test.model.User;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<User> userOpt = userService.authenticate(email, password);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String token = jwtUtils.generateToken(user.getEmail(), user.getRole());

            Map<String, Object> response = Map.of(
                    "id", user.getId(),
                    "email", user.getEmail(),
                    "first_name", user.getFirstName(),
                    "last_name", user.getLastName(),
                    "role", user.getRole(),
                    "token", token
            );

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        }
    }
    // Ajout dans AuthController.java

    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        System.out.println(" Tentative d'inscription : " + user.getEmail());
        return userService.register(user);
    }
    @PutMapping("/password/{userId}")
    public boolean updatePassword(@PathVariable Long userId, @RequestBody Map<String, String> payload) {
        String currentPassword = payload.get("currentPassword");
        String newPassword = payload.get("newPassword");
        return userService.updatePassword(userId, currentPassword, newPassword);
    }

}
