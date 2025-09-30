package com.test.movie_review.repository;

import com.test.movie_review.model.Movie;
import com.test.movie_review.model.Review;
import com.test.movie_review.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long> {

    Optional<Review> findByReviewIdAndUsers(Long reviewId, Users user);

    List<Review> findByMovie(Movie movie);
}
