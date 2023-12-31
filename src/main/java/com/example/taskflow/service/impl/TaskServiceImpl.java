package com.example.taskflow.service.impl;

import com.example.taskflow.DTO.TaskDoneDTO;
import com.example.taskflow.entity.Tag;
import com.example.taskflow.entity.Task;
import com.example.taskflow.entity.User;
import com.example.taskflow.exception.*;
import com.example.taskflow.repository.TagRepository;
import com.example.taskflow.repository.TaskRepository;
import com.example.taskflow.service.TaskService;
import com.example.taskflow.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TagRepository tagRepository;


    TaskServiceImpl(TaskRepository taskRepository,TagRepository tagRepository,UserService userService){
        this.taskRepository=taskRepository;
        this.tagRepository=tagRepository;
        this.userService=userService;
    }

    @Override
    public Task save(Task task){
        if(task.getStartDateTime().isBefore(LocalDateTime.now())){
            throw new DateValidationException("you can't add a task in the past");
        }
        if(task.getStartDateTime().isAfter(LocalDateTime.now().plusDays(3))){
            throw  new DateValidationException("you can't add a task after more than 3 days");
        }
        if(task.getStartDateTime().isAfter(task.getEndDateTime())){
            throw new DateValidationException("the start date time of task cannot be after end date time");
        }
        boolean taskByName=this.taskRepository.existsByName(task.getName());
        if(taskByName){
            throw new AlreadyExistsException("this task name is already exist");
        }

        if(task.getTags().size()<=1){
            throw new TagsCountException("the task must have two tags or more");
        }

        List<Tag> tags = task.getTags();
        for (Tag tag : tags) {
            tagRepository.save(tag);
        }
        System.out.println(task.getTags().get(0).getName());
        System.out.println(task.getTags().get(0).getId());
        return taskRepository.save(task);
    }

    @Override
    public Task markTaskAsDone(TaskDoneDTO taskDoneDTO){
        Optional<User> foundManger=userService.findById(taskDoneDTO.getUserId());
        if(foundManger.isEmpty()){
            throw new DoesNoExistException("this manager doesn't exist");
        }
        if(foundManger.get().getRole().getRole().equals("employee")){
            throw new AuthorizationException("this user is not manager");
        }
        Optional<Task> foundTask=taskRepository.findById(taskDoneDTO.getTaskId());
        if(foundTask.isEmpty()){
            throw new DoesNoExistException("this task doesn't exists");
        }
        if(foundTask.get().getStartDateTime().isAfter(LocalDateTime.now())){
            throw new StartAndEndTimeException("the task didn't begin yet");
        }
        if(foundTask.get().getEndDateTime().isBefore(LocalDateTime.now())){
            throw new StartAndEndTimeException("the deadline of task is over");
        }
        Task modifiedTask=foundTask.get();
        modifiedTask.setStatus("done");

        return taskRepository.save(modifiedTask);
    }
}
