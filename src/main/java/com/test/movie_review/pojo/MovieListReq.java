package com.test.movie_review.pojo;

import com.test.movie_review.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieListReq {

    private List<Movie> movies;
}
