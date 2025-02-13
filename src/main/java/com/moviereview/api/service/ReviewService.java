package com.moviereview.api.service;

import com.moviereview.api.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(int movieId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByMovieId(int id);
    ReviewDto getReviewById(int reviewId, int movieId);
    ReviewDto updateReview(int movieId, int reviewId, ReviewDto reviewDto);
    void deleteReview(int movieId, int reviewId);
}
