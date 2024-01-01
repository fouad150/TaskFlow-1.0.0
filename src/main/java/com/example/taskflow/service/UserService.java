package com.example.taskflow.service;

import com.example.taskflow.DTO.ReplacementDTO;
import com.example.taskflow.entity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    User register(User user);
    User login(User user);
    Optional<User> findById(Long id);
    void initializeReplacement();

    User save(User user);

    void initializeDeletion();

/*
    void increaseReplacement();
*/
}
