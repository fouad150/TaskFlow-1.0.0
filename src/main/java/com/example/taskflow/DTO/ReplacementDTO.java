package com.example.taskflow.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplacementDTO {
    @NotBlank(message = "the employee's id cannot be blank")
    @NotNull(message="the employee's id cannot be null")
    private Long employeeId;
    @NotBlank(message = "the task's id cannot be blank")
    @NotNull(message="the task's id cannot be null")
    private Long taskId;
}
