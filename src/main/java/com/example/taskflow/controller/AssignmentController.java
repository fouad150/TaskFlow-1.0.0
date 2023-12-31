package com.example.taskflow.controller;

import com.example.taskflow.DTO.AssignmentDTO;
import com.example.taskflow.entity.Assignment;
import com.example.taskflow.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/assignments")
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentController {
    private final AssignmentService assignmentService;
    AssignmentController(AssignmentService assignmentService){
        this.assignmentService=assignmentService;
    }

    @PostMapping
    public ResponseEntity<?> save(AssignmentDTO assignmentDTO){
        Assignment assignment=assignmentService.save(assignmentDTO);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }
}
