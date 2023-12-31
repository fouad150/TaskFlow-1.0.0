package com.example.taskflow.repository;

import com.example.taskflow.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
    boolean existsByName(String name);
}
