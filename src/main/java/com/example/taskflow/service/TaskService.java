package com.example.taskflow.service;

import com.example.taskflow.DTO.TaskDoneDTO;
import com.example.taskflow.entity.Task;

import java.util.Optional;

public interface TaskService {
    Task save(Task task);
    Task markTaskAsDone(TaskDoneDTO taskDoneDTO);
    Optional<Task> findById(Long id);
}
