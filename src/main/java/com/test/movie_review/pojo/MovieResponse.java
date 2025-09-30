package com.test.movie_review.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {


    private long movieId;
    private String tittle;
    private String description;
    private Date releaseDate;
    private String genre;

}
