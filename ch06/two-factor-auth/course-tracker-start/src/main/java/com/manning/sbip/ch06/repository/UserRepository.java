package com.manning.sbip.ch06.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch06.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByUsername(String username);
    User findByUsername(String username);
    User findByEmail(String email);
}
