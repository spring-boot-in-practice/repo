package com.manning.sbip.ch06.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch06.model.ApplicationUser;

@Repository
public interface UserRepository extends CrudRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);
}
