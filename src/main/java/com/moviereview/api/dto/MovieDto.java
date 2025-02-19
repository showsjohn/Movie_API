package com.moviereview.api.dto;
import lombok.Data;

@Data
public class MovieDto {
    private String id;
    private String title;
    private int year;
    private String description;
    private String poster;
    private String rating;
    private String studio;
}
