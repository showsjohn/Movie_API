package com.moviereview.api.controllers;

import com.moviereview.api.dto.ReviewDto;
import com.moviereview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/movie/{movieId}/reviews")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "movieId") String movieId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(movieId, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/movie/{movieId}/reviews")
    public List<ReviewDto> getReviewsByMovieId(@PathVariable(value = "movieId") String movieId) {
        return reviewService.getReviewsByMovieId(movieId);
    }

    @GetMapping("/movie/{movieId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "movieId") String movieId, @PathVariable(value = "id") String reviewId) {
        ReviewDto reviewDto = reviewService.getReviewById(movieId, reviewId);
        return new ResponseEntity<>(reviewDto, HttpStatus.OK);
    }

    @PutMapping("/movie/{movieId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "movieId") String movieId, @PathVariable(value = "id") String reviewId,
                                                  @RequestBody ReviewDto reviewDto) {
        ReviewDto updatedReview = reviewService.updateReview(movieId, reviewId, reviewDto);
        return new ResponseEntity<>(updatedReview, HttpStatus.OK);
    }

    @DeleteMapping("/movie/{movieId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable(value = "movieId") String movieId, @PathVariable(value = "id") String reviewId) {
        reviewService.deleteReview(movieId, reviewId);
        return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
    }
}