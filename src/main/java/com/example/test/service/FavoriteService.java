package com.example.test.service;

import com.example.test.model.Favorite;
import com.example.test.model.Product;
import com.example.test.repository.FavoriteRepository;
import com.example.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    public void toggleFavorite(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit introuvable avec l'ID : " + productId));


        Optional<Favorite> favorite = favoriteRepository.findByProductId(productId);

        if (favorite.isPresent()) {
            favoriteRepository.delete(favorite.get());
        } else {
            Favorite newFav = new Favorite();
            newFav.setProductId(productId);
            favoriteRepository.save(newFav);
        }
    }

    public boolean isFavorite(Long productId) {
        return favoriteRepository.existsByProductId(productId);
    }
    public List<Long> getFavoriteProductIds() {
        return favoriteRepository.findAll()
                .stream()
                .map(Favorite::getProductId)
                .toList();
    }
    public List<Product> getFavoriteProducts() {
        return favoriteRepository.findAll().stream()
                .map(fav -> productRepository.findById(fav.getProductId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }
    public void removeFavorite(Long productId) {
        favoriteRepository.findByProductId(productId)
                .ifPresent(favoriteRepository::delete);
    }

}
