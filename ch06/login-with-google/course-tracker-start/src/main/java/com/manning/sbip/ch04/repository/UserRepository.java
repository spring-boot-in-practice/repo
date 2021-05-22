package com.manning.sbip.ch04.repository;

import com.manning.sbip.ch04.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);
}
