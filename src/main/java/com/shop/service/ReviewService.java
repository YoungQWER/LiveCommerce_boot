package com.shop.service;

import com.shop.dto.ReviewFormDto;
import com.shop.entity.Order;
import com.shop.entity.Review;
import com.shop.repository.OrderRepository;
import com.shop.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void saveReview(ReviewFormDto reviewFormDto, String email) {
        Order order = orderRepository.findById(reviewFormDto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));

        Review review = new Review();
        review.setOrder(order);
        review.setRating(reviewFormDto.getRating());
        review.setComment(reviewFormDto.getComment());
        review.setEmail(email);

        reviewRepository.save(review);
    }
}
