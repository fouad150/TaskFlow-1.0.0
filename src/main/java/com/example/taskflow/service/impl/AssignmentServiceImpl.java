package com.example.taskflow.service.impl;

import com.example.taskflow.DTO.AssignmentDTO;
import com.example.taskflow.entity.Assignment;
import com.example.taskflow.repository.AssignmentRepository;
import com.example.taskflow.service.AssignmentService;
import org.springframework.stereotype.Service;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    AssignmentServiceImpl(AssignmentRepository assignmentRepository){
    }

    @Override
    public Assignment save(AssignmentDTO assignmentDTO){
        return null;
    }
}
