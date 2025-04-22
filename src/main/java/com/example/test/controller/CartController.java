package com.example.test.controller;

import com.example.test.model.CartItem;
import com.example.test.model.User;
import com.example.test.service.CartService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    // DTO pour recevoir les données du frontend
    public static class CartRequest {
        public Long userId;
        public Long productId;
        public int quantity;
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        return cartService.getCartItems(user); // doit filtrer : user_id = ?, session_id IS NULL
    }



    // ✅ POST : données envoyées dans le body JSON
    @PostMapping("/add")
    public void addOrUpdateCartItem(@RequestBody CartRequest request) {
        cartService.addOrUpdateCartItem(String.valueOf(request.userId), request.productId, request.quantity);
    }
    @PostMapping("/{userId}/add")
    public void addItemFromAngular(
            @PathVariable Long userId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        cartService.addOrUpdateCartItem(String.valueOf(userId), productId, quantity);
    }
    @PutMapping("/update")
    public void updateCartItem(@RequestBody CartRequest request) {
        cartService.addOrUpdateCartItem(String.valueOf(request.userId), request.productId, request.quantity);
    }
    @DeleteMapping("/remove/{userId}/{productId}")
    public void removeCartItemByPath(
            @PathVariable Long userId,
            @PathVariable Long productId
    ) {
        cartService.removeCartItem(userId, productId);
    }


    @DeleteMapping("/{userId}/remove")
    public void removeCartItem(@PathVariable Long userId, @RequestParam Long productId) {
        cartService.removeCartItem(userId, productId);
    }

    @DeleteMapping("/clear/{userId}")
    public void clearCart(@PathVariable Long userId) {
        System.out.println("Clear cart triggered for user ID: " + userId);
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        cartService.clearCart(user);
        System.out.println("Panier vidé pour " + user.getEmail());
    }


    @PostMapping("/guest/add")
    public void addToGuestCart(@RequestParam String sessionId, @RequestParam Long productId, @RequestParam int quantity) {
        cartService.addOrUpdateCartItem(sessionId, productId, quantity);
    }
    @GetMapping("/guest/{sessionId}")
    public ResponseEntity<List<CartItem>> getGuestCart(@PathVariable String sessionId) {
        List<CartItem> cartItems = cartService.getGuestCart(sessionId);
        return ResponseEntity.ok(cartItems);
    }
    @PutMapping("/guest/{sessionId}/update")
    public ResponseEntity<Void> updateGuestItem(
            @PathVariable String sessionId,
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        cartService.updateGuestCartItem(sessionId, productId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/guest/{sessionId}/remove")
    public ResponseEntity<Void> removeGuestItem(
            @PathVariable String sessionId,
            @RequestParam Long productId
    ) {
        cartService.removeGuestCartItem(sessionId, productId);
        return ResponseEntity.ok().build();
    }

}
