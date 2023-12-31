package com.example.taskflow.DTO;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDoneDTO {
    @NotBlank(message = "the user's id cannot be blank")
    @NotNull(message="the user's id cannot be null")
    private Long userId;
    @NotBlank(message = "the task's id cannot be blank")
    @NotNull(message="the task's id cannot be null")
    private Long taskId;
}
