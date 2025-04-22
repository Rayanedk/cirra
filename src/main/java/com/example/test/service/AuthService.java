package com.example.test.service;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).get();
        return user != null && user.getPassword().equals(password);
    }

    public boolean register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return false; // L'email est déjà utilisé
        }
        userRepository.save(user);
        return true;
    }
    // Méthode pour récupérer l'utilisateur connecté
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName(); // Récupère l'email de l'utilisateur authentifié
            return userRepository.findByEmail(email).get(); // Récupère l'utilisateur par son email
        }
        return null; // Aucun utilisateur authentifié
    }
    // Méthode pour déconnecter l'utilisateur
    public void logout() {
        // Invalidation de la session de l'utilisateur
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }
}
