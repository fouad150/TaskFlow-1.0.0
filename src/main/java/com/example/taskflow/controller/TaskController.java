package com.example.taskflow.controller;

import com.example.taskflow.DTO.TaskDTO;
import com.example.taskflow.DTO.TaskDoneDTO;
import com.example.taskflow.DTO.UserDTO;
import com.example.taskflow.DTO.UserResponseDTO;
import com.example.taskflow.convertor.TaskConverter;
import com.example.taskflow.convertor.UserConverter;
import com.example.taskflow.entity.Task;
import com.example.taskflow.entity.User;
import com.example.taskflow.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskService taskService;
    TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid TaskDTO taskDTO) {
        Task task= TaskConverter.convertToEntity(taskDTO);
        Task addedTask=taskService.save(task);
        TaskDTO addedTaskDTO =TaskConverter.convertToDTO(addedTask);
        return new ResponseEntity<>(addedTaskDTO, HttpStatus.OK);
    }

    @PostMapping("done")
    public ResponseEntity<?> save(@RequestBody @Valid TaskDoneDTO taskDoneDTO){
        taskService.markTaskAsDone(taskDoneDTO);
        return new ResponseEntity<>("the task marked as done",HttpStatus.OK);
    }
}
