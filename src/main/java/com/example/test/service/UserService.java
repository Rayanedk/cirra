package com.example.test.service;

import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    // Pour encoder les mots de passe

    public Optional<User> authenticate(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty(); // ne jamais throw ici
    }

    // Méthode de registre avec mot de passe haché
    public boolean register(User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("client");  // Role par défaut
        }

        // Hacher le mot de passe avant de l'enregistrer
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);  // Sauvegarder l'utilisateur dans la base de données
        return true;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean updatePassword(Long userId, String currentPassword, String newPassword) {
        return userRepository.findById(userId).map(user -> {
            // Vérifier si le mot de passe actuel correspond au haché
            if (passwordEncoder.matches(currentPassword, user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPassword));  // Hacher le nouveau mot de passe
                userRepository.save(user);
                return true;
            }
            return false;
        }).orElse(false);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
