package com.manning.sbip.ch06.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.manning.sbip.ch06.model.TotpDetails;

@Repository
public interface TotpRepository extends CrudRepository<TotpDetails, String> {

    TotpDetails findByUsername(String username);
    boolean existsByUsername(String username);
    Long deleteByUsername(String username);
}
