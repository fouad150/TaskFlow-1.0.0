package com.example.taskflow.service;

import com.example.taskflow.DTO.AssignmentDTO;
import com.example.taskflow.entity.Assignment;

public interface AssignmentService {
    Assignment save(AssignmentDTO assignmentDTO);

}
