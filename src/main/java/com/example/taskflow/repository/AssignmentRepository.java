package com.example.taskflow.repository;

import com.example.taskflow.entity.Assignment;
import com.example.taskflow.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment,Long> {
    Optional<Assignment> findByTask(Task task);
}
