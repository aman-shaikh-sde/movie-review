package com.test.movie_review.controller;
import com.test.movie_review.model.Users;
import com.test.movie_review.pojo.UserRegReq;
import com.test.movie_review.pojo.UserRes;
import com.test.movie_review.repository.UserRepo;
import com.test.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<UserRes> registerUser(@RequestBody UserRegReq userRegReq){

       UserRes userRes= userService.registerUser(userRegReq);
        return new ResponseEntity<>(userRes, HttpStatus.CREATED);
    }
}
