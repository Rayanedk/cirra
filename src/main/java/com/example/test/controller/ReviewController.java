package com.example.test.controller;

import com.example.test.dto.ReviewDto;
import com.example.test.model.Product;
import com.example.test.model.Review;
import com.example.test.repository.ProductRepository;
import com.example.test.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody ReviewDto dto) {
        System.out.println(">> RECU : " + dto);
        System.out.println("DTO -> " + dto);
        System.out.println("ProductId -> " + dto.getProductId());
        System.out.println("Satisfaction -> " + dto.getSatisfactionLevel());
        System.out.println("Comment -> " + dto.getAdditionalComment());

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Produit introuvable avec l’ID : " + dto.getProductId()));

        Review review = new Review();
        review.setProduct(product);
        review.setSatisfactionLevel(dto.getSatisfactionLevel());
        review.setProductSearch(dto.getProductSearch());
        review.setProblemsEncountered(dto.getProblemsEncountered());
        review.setPurchaseFor(dto.getPurchaseFor());
        review.setAdditionalComment(dto.getAdditionalComment());
        System.out.println("REVIEW FINAL -> " + review.getProduct());
        System.out.println("REVIEW FINAL -> " + review.getSatisfactionLevel());
        System.out.println("REVIEW FINAL -> " + review.getAdditionalComment());

        return ResponseEntity.ok(reviewService.submitReview(review));
    }
}
