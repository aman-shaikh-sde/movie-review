package com.test.movie_review.pojo;

import com.test.movie_review.model.Movie;
import com.test.movie_review.model.Users;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewReq {

    private String comments;
    private int rating;

}
