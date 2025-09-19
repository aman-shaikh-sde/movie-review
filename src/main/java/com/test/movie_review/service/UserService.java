package com.test.movie_review.service;

import com.test.movie_review.pojo.LoginReq;
import com.test.movie_review.pojo.UserRegReq;
import com.test.movie_review.pojo.UserRes;

public interface UserService {

    public UserRes registerUser(UserRegReq userRegReq);
    public String userLogin(LoginReq loginReq);

}
