package com.test.movie_review.pojo;

import com.test.movie_review.model.Movie;
import com.test.movie_review.model.Users;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewRes {

    private Long reviewId;
    private String username;
    private String comments;
    private LocalDateTime createdDate;
    private int rating;

}
