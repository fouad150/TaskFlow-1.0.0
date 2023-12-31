package com.example.taskflow.service.impl;

import com.example.taskflow.DTO.AssignmentDTO;
import com.example.taskflow.entity.Assignment;
import com.example.taskflow.entity.Task;
import com.example.taskflow.entity.User;
import com.example.taskflow.exception.AlreadyExistsException;
import com.example.taskflow.exception.AuthorizationException;
import com.example.taskflow.exception.DoesNoExistException;
import com.example.taskflow.exception.StartAndEndTimeException;
import com.example.taskflow.repository.AssignmentRepository;
import com.example.taskflow.service.AssignmentService;
import com.example.taskflow.service.TaskService;
import com.example.taskflow.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final UserService userService;
    private final TaskService taskService;
    AssignmentServiceImpl(AssignmentRepository assignmentRepository,UserService userService, TaskService taskService){
        this.assignmentRepository=assignmentRepository;
        this.userService=userService;
        this.taskService=taskService;
    }

    @Override
    public Assignment save(AssignmentDTO assignmentDTO){
        Optional<User> foundManger=userService.findById(assignmentDTO.getManagerId());
        Optional<User> foundEmployee=userService.findById(assignmentDTO.getEmployeeId());
        Optional<Task> foundTask=taskService.findById(assignmentDTO.getTaskId());
        if(foundManger.isEmpty()){
            throw new DoesNoExistException("this manager doesn't exist");
        }
        if(foundManger.get().getRole().getRole().equals("employee")){
            throw new AuthorizationException("there is no manager with this managerId");
        }
        if(foundEmployee.isEmpty()){
            throw new DoesNoExistException("this employee doesn't exist");
        }
        if(foundTask.isEmpty()){
            throw new DoesNoExistException("this task doesn't exist");
        }

        if(foundTask.get().getEndDateTime().isBefore(LocalDateTime.now())){
            throw new StartAndEndTimeException("the deadline of this task is over");
        }

        Optional<Assignment> foundAssignmentByTask=assignmentRepository.findByTask(foundTask.get());
        if(foundAssignmentByTask.isPresent()){
            throw new AlreadyExistsException("this task is already");
        }

        Assignment assignment=new Assignment(foundTask.get(),foundEmployee.get());

        return assignmentRepository.save(assignment);

    }
}
