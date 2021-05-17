package com.manning.sbip.ch06.service;

import com.manning.sbip.ch06.model.Verification;
import com.manning.sbip.ch06.repository.VerificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationService {

    private final VerificationRepository repository;

    @Autowired
    public VerificationService(VerificationRepository repository) {
        this.repository = repository;
    }

    public String getVerificationIdByUsername(String username) {
        Verification verification = repository.findByUsername(username);
        if(verification != null) {
            return verification.getVerificationId();
        }
        return null;
    }

    public String createVerification(String username) {
        if (!repository.existsByUsername(username)) {
            Verification verification = new Verification(username);
            verification = repository.save(verification);
            return verification.getVerificationId();
        }
        return getVerificationIdByUsername(username);
    }

    public String getUsernameForVerificationId(String verificationId) {
        Optional<Verification> verification = repository.findById(verificationId);
        if(verification.isPresent()) {
            return verification.get().getUsername();
        }
        return null;
    }
}
