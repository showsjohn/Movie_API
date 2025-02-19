package com.moviereview.api.respository;

import com.moviereview.api.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, String> {
    
}
