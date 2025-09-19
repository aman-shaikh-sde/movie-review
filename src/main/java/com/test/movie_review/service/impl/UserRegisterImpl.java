package com.test.movie_review.service.impl;

import com.test.movie_review.model.Role;
import com.test.movie_review.model.Users;
import com.test.movie_review.pojo.LoginReq;
import com.test.movie_review.pojo.UserRegReq;
import com.test.movie_review.pojo.UserRes;
import com.test.movie_review.repository.RoleRepo;
import com.test.movie_review.repository.UserRepo;
import com.test.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRegisterImpl implements UserService {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Override
    public UserRes registerUser(UserRegReq userRegReq) {

        if(userRepo.existsByEmail(userRegReq.getEmail())){
            throw new RuntimeException("Email is Already Registered");
        }
        if(userRepo.existsByUsername(userRegReq.getUsername())){
            throw new RuntimeException("Username Already Exists");
        }

        Role userRole=roleRepo.findByName("ROLE_USER")
                .orElseThrow(()->new RuntimeException("Role User Not Found"));


        Users users=Users.builder()
                .username(userRegReq.getUsername())
                .email(userRegReq.getEmail())
                .password(userRegReq.getPassword())
                .roles(Set.of(userRole))
                .createdAt(LocalDateTime.now())
                .build();

        Users savedUser=userRepo.save(users);

        return UserRes.builder()
                .id(savedUser.getUserId())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .build();


    }

    @Override
    public String userLogin(LoginReq loginReq) {

        if()
        return "";
    }
}
