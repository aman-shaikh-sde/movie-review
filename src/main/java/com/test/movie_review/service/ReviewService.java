package com.test.movie_review.service;

import com.test.movie_review.pojo.ReviewReq;
import com.test.movie_review.pojo.ReviewRes;

public interface ReviewService {

    public ReviewRes addReview(ReviewReq reviewReq,Long id);
}
