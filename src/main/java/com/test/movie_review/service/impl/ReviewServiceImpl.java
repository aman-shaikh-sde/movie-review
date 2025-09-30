package com.test.movie_review.service.impl;

import com.test.movie_review.model.Movie;
import com.test.movie_review.model.Review;
import com.test.movie_review.model.Users;
import com.test.movie_review.pojo.ReviewReq;
import com.test.movie_review.pojo.ReviewRes;
import com.test.movie_review.pojo.UserRegReq;
import com.test.movie_review.repository.MovieRepo;
import com.test.movie_review.repository.ReviewRepo;
import com.test.movie_review.repository.UserRepo;
import com.test.movie_review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.management.RuntimeMBeanException;
import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.core.ErrorReportConfiguration.builder;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;

    private  final MovieRepo movieRepo;

    private final UserRepo userRepo;


    @Override
    public ReviewRes addReview(ReviewReq reviewReq, Long movieId, Long userId) {

        Movie movie= movieRepo.findById(movieId)
                .orElseThrow(()->new RuntimeException("Movie Not Found"));

        Users user= userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User Not Found"));

        Review review =Review.builder()
                .comment(reviewReq.getComments())
                .movie(movie)
                .users(user)
                .createdAt(LocalDateTime.now())
                .rating(reviewReq.getRating())
                .build();
Review savedReview=reviewRepo.save(review);

        return ReviewRes.builder()
                .reviewId(savedReview.getReviewId())
                .comments(savedReview.getComment())
                .rating(savedReview.getRating())
                .createdDate(LocalDateTime.now())
                .username(savedReview.getUsers().getUsername())
                .build();
    }

    @Override
    public ReviewRes updateReview( Long reviewId,String username, ReviewReq reviewReq) {

        Users user=userRepo.findByUsername(username)
        .orElseThrow(()->new RuntimeException("User Not Found"));


        Review review=reviewRepo.findByReviewIdAndUsers(reviewId,user)
                .orElseThrow(()->new RuntimeException("Review Not Found"));

        review.setComment(reviewReq.getComments());
        review.setRating(reviewReq.getRating());
        Review updatedReview =reviewRepo.save(review);

        return ReviewRes.builder()
                .reviewId(updatedReview .getReviewId())
                .rating(updatedReview .getRating())
                .comments(updatedReview .getComment())
                .username(updatedReview .getUsers().getUsername())
                .createdDate(updatedReview .getCreatedAt())
                .build();
    }


    @Override
    public List<ReviewRes> getReviewBymovieId(Long movieId) {

        Movie movie=movieRepo.findById(movieId)
                .orElseThrow(()->new RuntimeException("Movie NOt Found"));

       List<Review> reviews=reviewRepo.findByMovie(movie);

       return reviews.stream()
               .map(review->ReviewRes.builder()
                       .reviewId(review.getReviewId())
                       .comments(review.getComment())
                       .rating(review.getRating())
                       .createdDate(review.getCreatedAt())
                       .username(review.getUsers().getUsername())
                       .build())
               .toList();

    }

    @Override
    public String deleteReview(Long reviewId, String username) {

        Users users=userRepo.findByUsername(username)
                .orElseThrow(()->new RuntimeException("User Not Found"));

        Review review=reviewRepo.findByReviewIdAndUsers(reviewId,users)
                .orElseThrow(()->new RuntimeException("User or Review Not Found"));
        reviewRepo.delete(review);

        return "Review Deleted Successfully";
    }
}


