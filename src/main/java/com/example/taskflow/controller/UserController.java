package com.example.taskflow.controller;

import com.example.taskflow.DTO.UserDTO;
import com.example.taskflow.DTO.UserResponseDTO;
import com.example.taskflow.convertor.UserConverter;
import com.example.taskflow.entity.User;
import com.example.taskflow.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;
    UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {
        User user= UserConverter.convertToEntity(userDTO);
        User addedUser=userService.register(user);
        UserResponseDTO addedUserDTO =UserConverter.convertToDTO(addedUser);
        return new ResponseEntity<>(addedUserDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserDTO userDTO) {
        User user= UserConverter.convertToEntity(userDTO);
        User foundUser=userService.login(user);
        UserResponseDTO foundUserDTO =UserConverter.convertToDTO(foundUser);
        return new ResponseEntity<>(foundUserDTO, HttpStatus.OK);
    }


    @Scheduled(cron = "0 0 0 * * *") // Runs at 12:00 AM each day
    public void initializeReplacementValue() {
        userService.initializeReplacement();
    }


    @Scheduled(cron = "0 0 0 1 * *") // Runs at 12:00 AM on the 1st day of each month
    public void initializeDeletion() {
        userService.initializeDeletion();
    }

    /*@PostConstruct
    @Scheduled(fixedRate = 1000*60) // Runs every minute
    public void incrementReplacement() {
        userService.increaseReplacement();
    }*/
}
