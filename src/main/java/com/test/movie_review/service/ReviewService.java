package com.test.movie_review.service;

import com.test.movie_review.pojo.ReviewReq;
import com.test.movie_review.pojo.ReviewRes;

import java.util.List;

public interface ReviewService {

    public ReviewRes addReview(ReviewReq reviewReq,Long movieId,Long userId);
    public List<ReviewRes> getReviewBymovieId(Long movieId);
    public ReviewRes updateReview(Long reviewId,String username,ReviewReq reviewReq);
    public String deleteReview(Long reviewId,String username);
}
