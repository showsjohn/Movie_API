package com.moviereview.api.service;

import com.moviereview.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(String movieId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByMovieId(String id);
    ReviewDto getReviewById(String reviewId, String movieId);
    ReviewDto updateReview(String movieId, String reviewId, ReviewDto reviewDto);
    void deleteReview(String movieId, String reviewId);
}
