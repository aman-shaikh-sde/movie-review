package com.test.movie_review.controller;

import com.test.movie_review.pojo.ReviewReq;
import com.test.movie_review.pojo.ReviewRes;
import com.test.movie_review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class ReviewController {


    private final ReviewService reviewService;


    @PostMapping("/{movieId}/reviews")
    public ResponseEntity<ReviewRes> addReview(@RequestBody ReviewReq reviewReq,
                                               @PathVariable Long movieId,
                                               @RequestParam Long userId) {

        return new ResponseEntity<>(reviewService.addReview(reviewReq, movieId, userId), HttpStatus.CREATED);

    }

    @GetMapping("/{movieId}/reviews")
    public ResponseEntity<List<ReviewRes>> getReviewByMovie(@PathVariable Long movieId) {

        return new ResponseEntity<>(reviewService.getReviewBymovieId(movieId), HttpStatus.OK);
    }


    @PutMapping("/{reviewId}/review/update")
    public ResponseEntity<ReviewRes> updateReview(@PathVariable Long reviewId,
                                                  @RequestBody ReviewReq reviewReq,
                                                  Principal principal){

        return new ResponseEntity<>(reviewService.updateReview(reviewId,principal.getName(),reviewReq),HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}/review/delete")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId,
                                               Principal principal){
        return new ResponseEntity<>(reviewService.deleteReview(reviewId,principal.getName()),HttpStatus.OK);
    }

}
