package com.moviereview.api.service;

import com.moviereview.api.dto.MovieDto;
import com.moviereview.api.dto.MovieResponse;

public interface MovieService {
    MovieDto createMovie(MovieDto movieDto);
    MovieResponse getAllMovies(int pageNo, int pageSize);
    MovieDto getMovieById(String id);
    MovieDto updateMovie(MovieDto movieDto, String id);
    void deleteMovie(String id);

}
