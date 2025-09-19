package com.test.movie_review.controller;

import com.test.movie_review.model.Movie;
import com.test.movie_review.pojo.MovieListReq;
import com.test.movie_review.pojo.MovieRequest;
import com.test.movie_review.pojo.MovieResponse;
import com.test.movie_review.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;


    @PostMapping("/bulk/")
    public ResponseEntity<List<MovieResponse>> addMultipleMovie(@Valid @RequestBody MovieListReq movieRequest){
        List<MovieResponse> movieResponses=movieService.addMultipleMovies(movieRequest);
        return new ResponseEntity<>(movieResponses,HttpStatus.CREATED);
    }




    @PostMapping("/")
    public ResponseEntity<MovieResponse> addMovie(@RequestBody MovieRequest movieRequest){

        MovieResponse movie=movieService.addMovie(movieRequest);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }


    @GetMapping("/")
    public  ResponseEntity<Page<MovieResponse>> getAllmovie(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "5" )int size
                                                            ){


        Page<MovieResponse> movie=movieService.getAllMovies(page,size);
        return new  ResponseEntity<>(movie,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id){
        MovieResponse movieResponse=movieService.getById(id);
        return new ResponseEntity<>(movieResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> updateMovie(@RequestBody MovieRequest movieRequest,@PathVariable Long id){
        MovieResponse movieResponse=movieService.updateMovie(movieRequest,id);
        return new ResponseEntity<>(movieResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id){
        String deleted=movieService.deleteMovie(id);
        return new ResponseEntity<>(deleted,HttpStatus.OK);
    }
}
