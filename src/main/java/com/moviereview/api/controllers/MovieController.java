package com.moviereview.api.controllers;
import com.moviereview.api.dto.MovieDto;
import com.moviereview.api.dto.MovieResponse;
import com.moviereview.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("movie")
    public ResponseEntity<MovieResponse> getMovie(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue ="10", required = false) int pageSize
    ) {

        return new ResponseEntity<>(movieService.getAllMovies(pageNo, pageSize), HttpStatus.OK);

    }

    @GetMapping("movie/{id}")
    public ResponseEntity<MovieDto> movieDetail(@PathVariable int id) {
        return new ResponseEntity<>(movieService.getMovieById(id),HttpStatus.OK);
    }

    @PostMapping("movie/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movieDto) {

        return new ResponseEntity<>(movieService.createMovie(movieDto), HttpStatus.CREATED);
    }

    @PutMapping("movie/{id}/update")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movieDto, @PathVariable("id") int movieId) {
        MovieDto response = movieService.updateMovie(movieDto, movieId);
        return new ResponseEntity<>(movieDto, HttpStatus.OK);
    }

    @DeleteMapping("movie/{id}/delete")
    public ResponseEntity<String> deleteMovie(@PathVariable("id") int movieId) {
        movieService.deleteMovie(movieId);
        return new ResponseEntity<>("Movie deleted", HttpStatus.NO_CONTENT);
    }

}
