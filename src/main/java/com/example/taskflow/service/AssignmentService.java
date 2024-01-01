package com.example.taskflow.service;

import com.example.taskflow.DTO.AdditionalAssignmentDTO;
import com.example.taskflow.DTO.AssignmentDTO;
import com.example.taskflow.DTO.ReplacementDTO;
import com.example.taskflow.entity.Assignment;

public interface AssignmentService {
    Assignment save(AssignmentDTO assignmentDTO);
    Assignment addAdditionalAssignment(AdditionalAssignmentDTO additionalAssignmentDTO);
    void replaceTask(ReplacementDTO replacementDTO);
}
