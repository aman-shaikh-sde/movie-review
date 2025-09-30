package com.test.movie_review.service;

import com.test.movie_review.pojo.MovieListReq;
import com.test.movie_review.pojo.MovieRequest;
import com.test.movie_review.pojo.MovieResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MovieService {

    public MovieResponse addMovie(MovieRequest movieRequest);
    public Page<MovieResponse> getAllMovies(int page,int size);

    List<MovieResponse> addMultipleMovies(MovieListReq movieRequest);

    public MovieResponse getById(Long movieId);

    public MovieResponse updateMovie(MovieRequest movieRequest,Long movieId);
    public String deleteMovie(Long movieId);
}
