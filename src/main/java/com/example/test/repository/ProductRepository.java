package com.example.test.repository;

import com.example.test.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryIgnoreCase(String category);
    List<Product> findTop4ByOrderByIdDesc(); // ou un champ `isFeatured`

}
