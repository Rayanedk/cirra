package com.example.test.service;

import com.example.test.model.CartItem;
import com.example.test.model.Product;
import com.example.test.model.User;
import com.example.test.repository.CartItemRepository;
import com.example.test.repository.ProductRepository;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUserWithoutSessionId(user);
    }

    public void addOrUpdateCartItem(String idOrSession, Long productId, int quantity) {
        try {
            Long userId = Long.parseLong(idOrSession);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

            CartItem item = cartItemRepository.findByUserAndProduct(user, product)
                    .orElse(null);

            if (item != null) {
                item.setQuantity(quantity); // ✅ met à jour directement
            } else {
                item = new CartItem();
                item.setUser(user);
                item.setProduct(product);
                item.setQuantity(quantity);
            }

            item.setSessionId(null);
            cartItemRepository.save(item);
        } catch (NumberFormatException e) {
            // ➤ Sinon, c’est un invité
            String sessionId = idOrSession;

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

            CartItem item = cartItemRepository.findBySessionIdAndProduct(sessionId, product)
                    .orElse(null);

            if (item != null) {
                item.setQuantity(quantity); // ✅ met à jour directement aussi ici
            } else {
                item = new CartItem();
                item.setSessionId(sessionId);
                item.setProduct(product);
                item.setQuantity(quantity);
            }

            item.setUser(null); // 🛑 corrige bien ici
            cartItemRepository.save(item);
        }
    }

    public void removeCartItem(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new RuntimeException("Article du panier non trouvé"));
        cartItemRepository.delete(cartItem);
    }

    public List<CartItem> getGuestCart(String sessionId) {
        // logique pour retourner les articles du panier invité depuis la session ou la base de données
        return cartItemRepository.findBySessionId(sessionId);
    }

    public void clearCart(User user) {
        List<CartItem> items = cartItemRepository.findByUser(user);
        cartItemRepository.deleteAllByUser(user); // 🔥 supprime vraiment en DB
    }

    public void updateGuestCartItem(String sessionId, Long productId, int quantity) {
        CartItem item = cartItemRepository.findBySessionIdAndProductId(sessionId, productId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }

    public void removeGuestCartItem(String sessionId, Long productId) {
        CartItem item = cartItemRepository.findBySessionIdAndProductId(sessionId, productId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));
        cartItemRepository.delete(item);
    }

}
