package com.manning.sbip.ch06.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch06.model.Verification;

@Repository
public interface VerificationRepository extends CrudRepository<Verification, String> {

    Verification findByUsername(String userName);
    boolean existsByUsername(String userName);
}
