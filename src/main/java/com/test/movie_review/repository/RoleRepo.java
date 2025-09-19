package com.test.movie_review.repository;

import com.test.movie_review.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {


    Optional<Role> findByName(String name);
}
