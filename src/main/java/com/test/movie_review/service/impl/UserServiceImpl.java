package com.test.movie_review.service.impl;

import com.test.movie_review.model.Role;
import com.test.movie_review.model.Users;
import com.test.movie_review.pojo.LoginReq;
import com.test.movie_review.pojo.UserRegReq;
import com.test.movie_review.pojo.UserRes;
import com.test.movie_review.repository.RoleRepo;
import com.test.movie_review.repository.UserRepo;
import com.test.movie_review.security.JwtUtil;
import com.test.movie_review.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

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
                .password(passwordEncoder.encode(userRegReq.getPassword()))
                .roles(Set.of(userRole))
                .createdAt(LocalDateTime.now())
                .build();

        Users savedUser=userRepo.save(users);

        return UserRes.builder()
                .userId(savedUser.getUserId())
                .email(savedUser.getEmail())
                .username(savedUser.getUsername())
                .build();


    }

    @Override
    public String userLogin(LoginReq loginReq) {

        Users user=userRepo.findByUsername(loginReq.getUsername())
                .orElseThrow(()->new RuntimeException("Invalid Username or Password"));

        if(!passwordEncoder.matches(loginReq.getPassword(),user.getPassword())){
            throw new RuntimeException(("Invalid Username or Password"));
        }
        return  jwtUtil.generateToken(user.getUsername());
    }


    @Override
    public UserRes getCurrentUser(String username) {

        Users user=userRepo.findByUsername(username).orElseThrow(()-> new RuntimeException("Username Not Found"));

        return UserRes.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();

    }




}
