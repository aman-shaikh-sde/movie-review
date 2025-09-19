package com.test.movie_review.service.impl;

import com.test.movie_review.MovieReviewApplication;
import com.test.movie_review.model.Movie;
import com.test.movie_review.pojo.MovieListReq;
import com.test.movie_review.pojo.MovieRequest;
import com.test.movie_review.pojo.MovieResponse;
import com.test.movie_review.repository.MovieRepo;
import com.test.movie_review.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepo movieRepo;


    @Override
    public List<MovieResponse> addMultipleMovies(MovieListReq movieRequest) {

        List<Movie> movies=movieRequest.getMovies().stream()
                .map(req->Movie.builder()
                        .tittle(req.getTittle())
                        .genre(req.getGenre())
                        .description(req.getDescription())
                        .build()).toList();

        List<Movie> savedMovie=movieRepo.saveAll(movies);
        return savedMovie.stream()
                .map(movie -> MovieResponse.builder()
                        .id(movie.getId())
                        .tittle(movie.getTittle())
                        .description(movie.getDescription())
                        .genre(movie.getGenre())
                        .releaseDate(movie.getReleaseDate())
                        .build()).toList();

    }

    @Override
    public MovieResponse addMovie(MovieRequest movieRequest) {

        Movie movie=Movie.builder()
                .tittle(movieRequest.getTittle())
                .description(movieRequest.getDescription())
                .genre(movieRequest.getGenre())
                .build();

        Movie savedMovie=movieRepo.save(movie);
        return MovieResponse.builder()
                .id(savedMovie.getId())
                .tittle(savedMovie.getTittle())
                .description(savedMovie.getDescription())
                .genre(savedMovie.getGenre())
                .releaseDate(savedMovie.getReleaseDate())
                .build();
    }

    @Override
    public Page<MovieResponse> getAllMovies(int page,int size) {

        Pageable pageable= PageRequest.of(page,size, Sort.by("id").ascending());
        Page<Movie> moviePage =movieRepo.findAll(pageable);
      return moviePage.map(movie->MovieResponse.builder()
              .id(movie.getId())
              .tittle(movie.getTittle())
              .genre(movie.getGenre())
              .description(movie.getDescription())
              .releaseDate(movie.getReleaseDate())
              .build());

    }


    @Override
    public MovieResponse getById(Long id) {

        Movie movie=movieRepo.findById(id).orElseThrow(()-> new RuntimeException("Movie Not Found with: "+id));

        return MovieResponse.builder()
                .id(movie.getId())
                .tittle(movie.getTittle())
                .genre(movie.getGenre())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .build();
    }

    @Override
    public MovieResponse updateMovie(MovieRequest movieRequest, Long id) {

        Movie movie=movieRepo.findById(id).orElseThrow(()->new RuntimeException("Movie Not Found With id: "+id));

        movie.setTittle(movieRequest.getTittle());
        movie.setDescription(movieRequest.getDescription());
        movie.setGenre(movieRequest.getGenre());

        Movie update=movieRepo.save(movie);
        return MovieResponse.builder()
                .id(update.getId())
                .tittle(update.getTittle())
                .description(update.getDescription())
                .releaseDate(update.getReleaseDate())
                .genre(update.getGenre())
                .build();
    }

    @Override
    public String deleteMovie(Long id) {
        Movie movie=movieRepo.findById(id).orElseThrow(()->new RuntimeException("Not Found "));

        movieRepo.delete(movie);
        return "Movie with id: "+id+" Deleted Successfully";
    }
}
