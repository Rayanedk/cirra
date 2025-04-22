package com.example.test.service;

import com.example.test.model.Review;
import com.example.test.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review submitReview(Review review) {
        Review saved = reviewRepository.save(review);
        System.out.println(">> SAUVÉ ID : " + saved.getId());
        return saved;
    }
}
