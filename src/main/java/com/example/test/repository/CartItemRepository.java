package com.example.test.repository;

import com.example.test.model.CartItem;
import com.example.test.model.Product;
import com.example.test.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
    Optional<CartItem> findBySessionIdAndProduct(String sessionId, Product product);
    List<CartItem> findBySessionId(String sessionId);
    Optional<CartItem> findBySessionIdAndProductId(String sessionId, Long productId);
    @Transactional
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.user = :user")
    void deleteAllByUser(@Param("user") User user);
    @Query("SELECT c FROM CartItem c WHERE c.user = :user AND c.sessionId IS NULL")
    List<CartItem> findByUserWithoutSessionId(@Param("user") User user);


}
