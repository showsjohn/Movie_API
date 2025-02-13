package com.moviereview.api.dto;

import lombok.Data;
import java.util.List;

@Data
public class MovieResponse {
    List<MovieDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLastPage;
}
