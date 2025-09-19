package com.test.movie_review.service.impl;

import com.test.movie_review.pojo.ReviewReq;
import com.test.movie_review.pojo.ReviewRes;
import com.test.movie_review.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Override
    public ReviewRes addReview(ReviewReq reviewReq, Long id) {
        return null;
    }
}
