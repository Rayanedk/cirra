package com.example.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 👉 change ici le mot de passe à hasher
        String rawPassword = "admin123";

        String hashedPassword = encoder.encode(rawPassword);
        System.out.println("Mot de passe hashé : " + hashedPassword);
    }

}
