package com.example.taskflow.convertor;

import com.example.taskflow.DTO.TaskDTO;
import com.example.taskflow.DTO.UserResponseDTO;
import com.example.taskflow.entity.Task;
import com.example.taskflow.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class TaskConverter {
    public static Task convertToEntity(TaskDTO taskDTO){
         Task task=new Task();
         task.setName(taskDTO.getName());
         task.setDescription(taskDTO.getDescription());
         task.setStartDateTime(taskDTO.getStartDateTime());
         task.setEndDateTime(taskDTO.getEndDateTime());
         task.setStatus("not done");
         task.setTags(taskDTO.getTags());
         return task;
    }

    public static TaskDTO convertToDTO(Task task){
        TaskDTO taskDTO=new TaskDTO();
        taskDTO.setName(task.getName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStartDateTime(task.getStartDateTime());
        taskDTO.setEndDateTime(task.getEndDateTime());
        taskDTO.setTags(task.getTags());
        return taskDTO;
    }

    public static List<TaskDTO> convertToDTOList(List<Task> tasks) {
        return tasks.stream()
                .map(TaskConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
