package com.moviereview.api.service.impl;

import com.moviereview.api.dto.MovieDto;
import com.moviereview.api.dto.MovieResponse;

import com.moviereview.api.exceptions.MovieNotFoundException;
import com.moviereview.api.models.Movie;
import com.moviereview.api.respository.MovieRepository;
import com.moviereview.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setYear(movieDto.getYear());
        movie.setDescription(movieDto.getDescription());
        movie.setRating(movieDto.getRating());
        movie.setStudio(movieDto.getStudio());

        Movie movieSaved = movieRepository.save(movie);

        MovieDto movieResponse = new MovieDto();
        movieResponse.setId(movieSaved.getId());
        movieResponse.setTitle(movieSaved.getTitle());
        movieResponse.setYear(movieSaved.getYear());
        movieResponse.setDescription(movieSaved.getDescription());
        movieResponse.setRating(movieSaved.getRating());
        movieResponse.setStudio(movieSaved.getStudio());

        return movieResponse;
    }

    @Override
    public MovieResponse getAllMovies(int pageNo, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize);
        Page<Movie> moviePage = movieRepository.findAll(pageRequest);
        List<Movie> movieList = moviePage.getContent();
        List<MovieDto> content = movieList.stream().map(this::mapToDto).collect(Collectors.toList());

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setContent(content);
        movieResponse.setPageNo(moviePage.getNumber());
        movieResponse.setPageSize(moviePage.getSize());
        movieResponse.setTotalElements(moviePage.getTotalElements());
        movieResponse.setTotalPages(moviePage.getTotalPages());
        movieResponse.setLastPage(moviePage.isLast());
        return movieResponse;
    }

    @Override
    public MovieDto getMovieById(String id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found!"));
        return mapToDto(movie);
    }

    @Override
    public MovieDto updateMovie(MovieDto movieDto, String id) {
      Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found! Could not be updated!"));

      movie.setTitle(movieDto.getTitle());
      movie.setYear(movieDto.getYear());
      movie.setDescription(movieDto.getDescription());
      movie.setPoster(movieDto.getPoster());
      movie.setRating(movieDto.getRating());
      movie.setStudio(movieDto.getStudio());

      Movie movieSaved = movieRepository.save(movie);
      return mapToDto(movieSaved);
    }

    @Override
    public void deleteMovie(String id) {
        try {
            movieRepository.deleteById(id);
        } catch (Exception e) {
            throw new MovieNotFoundException("Movie not found! Could not be deleted!");
        }
    }

    private MovieDto mapToDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setTitle(movie.getTitle());
        movieDto.setYear(movie.getYear());
        movieDto.setDescription(movie.getDescription());
        movieDto.setPoster(movie.getPoster());
        movieDto.setStudio(movie.getStudio());
        movieDto.setRating(movie.getRating());

        return movieDto;
    }

    private Movie mapToEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setTitle(movieDto.getTitle());
        movie.setYear(movieDto.getYear());
        movie.setDescription(movieDto.getDescription());
        movie.setPoster(movieDto.getPoster());
        movie.setStudio(movieDto.getStudio());
        movie.setRating(movieDto.getRating());

        return movie;
    }
}
