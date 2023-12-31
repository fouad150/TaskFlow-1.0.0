package com.example.taskflow.service;

import com.example.taskflow.DTO.TaskDoneDTO;
import com.example.taskflow.entity.Task;

public interface TaskService {
    Task save(Task task);
    Task markTaskAsDone(TaskDoneDTO taskDoneDTO);
}
