package com.example.taskflow.controller;

import com.example.taskflow.DTO.AdditionalAssignmentDTO;
import com.example.taskflow.DTO.AssignmentDTO;
import com.example.taskflow.entity.Assignment;
import com.example.taskflow.service.AssignmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/assignments")
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentController {
    private final AssignmentService assignmentService;
    AssignmentController(AssignmentService assignmentService){
        this.assignmentService=assignmentService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AssignmentDTO assignmentDTO){
        System.out.println(assignmentDTO.getManagerId());
        System.out.println("taskId "+assignmentDTO.getTaskId());
        Assignment assignment=assignmentService.save(assignmentDTO);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }

    @PostMapping("/additionalAssignment")
    public ResponseEntity<?> addAdditionalAssignment(@RequestBody @Valid AdditionalAssignmentDTO additionalAssignmentDTO){
        Assignment additionalAssignment=assignmentService.addAdditionalAssignment(additionalAssignmentDTO);
        return new ResponseEntity<>(additionalAssignment, HttpStatus.OK);
    }
}
