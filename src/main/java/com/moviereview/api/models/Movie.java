package com.moviereview.api.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "movie")
public class Movie {
    @Id
    private String id;
    private String title;
    private String studio;
    private String rating;
    private int year;
    private String description;
    private String poster;


    private List<Review> reviews = new ArrayList<Review>();
}
