package com.moviereview.api.exceptions;

public class MovieNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public MovieNotFoundException(String message) {
        super(message);
    }


}
