package com.example.test.controller;

import com.example.test.model.Favorite;
import com.example.test.model.Product;
import com.example.test.repository.UserRepository;
import com.example.test.security.JwtUtils;
import com.example.test.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "http://localhost:4200")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;

    // 🔁 Récupérer tous les favoris (anonymes ou connectés indistinctement)
    @GetMapping
    public List<Favorite> getAllFavorites() {
        return favoriteService.getAllFavorites();
    }

    // ✅ Ajouter ou retirer un produit des favoris
    @PostMapping("/toggle/{productId}")
    public ResponseEntity<Void> toggleFavorite(@PathVariable Long productId) {
        favoriteService.toggleFavorite(productId);
        return ResponseEntity.ok().build();
    }

    // ❓ Vérifie si le produit est en favori
    @GetMapping("/exists/{productId}")
    public ResponseEntity<Boolean> isFavorite(@PathVariable Long productId) {
        return ResponseEntity.ok(favoriteService.isFavorite(productId));
    }
    @GetMapping("/ids")
    public List<Long> getFavoriteIds() {
        return favoriteService.getFavoriteProductIds();
    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getFavoriteProducts() {
        return ResponseEntity.ok(favoriteService.getFavoriteProducts());
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long productId) {
        favoriteService.removeFavorite(productId);
        return ResponseEntity.ok().build();
    }

}
