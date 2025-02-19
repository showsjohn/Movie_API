package com.moviereview.api.service.impl;

import com.moviereview.api.dto.ReviewDto;
import com.moviereview.api.exceptions.MovieNotFoundException;
import com.moviereview.api.exceptions.ReviewNotFoundException;
import com.moviereview.api.models.Movie;
import com.moviereview.api.models.Review;
import com.moviereview.api.respository.MovieRepository;
import com.moviereview.api.respository.ReviewRepository;
import com.moviereview.api.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private MovieRepository movieRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public ReviewDto createReview(String movieId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("movie with associated review not found"));

        review.setMovie(movie);

        Review newReview = reviewRepository.save(review);

        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByMovieId(String id) {
        List<Review> reviews = reviewRepository.findByMovieId(id);

        return reviews.stream().map(review -> mapToDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(String reviewId, String movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associate movie not found"));

        if(review.getMovie().getId() != movie.getId()) {
            throw new ReviewNotFoundException("This review does not belond to a movie");
        }

        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(String movieId, String reviewId, ReviewDto reviewDto) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("movie with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associate movie not found"));

        if(review.getMovie().getId() != movie.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a movie");
        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review updateReview = reviewRepository.save(review);

        return mapToDto(updateReview);
    }

    @Override
    public void deleteReview(String movieId, String reviewId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException("Movie with associated review not found"));

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException("Review with associated movie not found"));

        if(review.getMovie().getId() != movie.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a movie");
        }

        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}