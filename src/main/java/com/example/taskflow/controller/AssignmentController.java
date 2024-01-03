package com.example.taskflow.controller;

import com.example.taskflow.DTO.AdditionalAssignmentDTO;
import com.example.taskflow.DTO.AssignmentDTO;
import com.example.taskflow.DTO.DeletionDTO;
import com.example.taskflow.DTO.ReplacementDTO;
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

        Assignment assignment=assignmentService.save(assignmentDTO);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }

    @PostMapping("/additionalAssignment")
    public ResponseEntity<?> addAdditionalAssignment(@RequestBody @Valid AdditionalAssignmentDTO additionalAssignmentDTO){
        Assignment additionalAssignment=assignmentService.addAdditionalAssignment(additionalAssignmentDTO);
        return new ResponseEntity<>(additionalAssignment, HttpStatus.OK);
    }

    @PostMapping("/replace-task")
    public ResponseEntity<?> replaceTask(@RequestBody @Valid ReplacementDTO replacementDTO){
        assignmentService.replaceTask(replacementDTO);
        return new ResponseEntity<>("the replacement has been done successfully",HttpStatus.OK);
    }


    @PostMapping("/delete-task")
    public ResponseEntity<?> deleteTask(@RequestBody @Valid DeletionDTO deletionDTO){
        assignmentService.deleteTask(deletionDTO);
        return new ResponseEntity<>("the deletion has been done successfully",HttpStatus.OK);
    }

}
