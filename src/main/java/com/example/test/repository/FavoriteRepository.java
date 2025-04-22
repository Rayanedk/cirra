package com.example.test.repository;

import com.example.test.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Optional<Favorite> findByProductId(Long productId);
    boolean existsByProductId(Long productId);
}
