package com.test.movie_review.repository;

import com.test.movie_review.model.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
