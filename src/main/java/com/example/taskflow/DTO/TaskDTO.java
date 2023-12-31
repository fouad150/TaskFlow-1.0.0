package com.example.taskflow.DTO;

import com.example.taskflow.entity.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    @NotBlank(message = "the startDateTime cannot be blank")
    @NotNull(message="the startDateTime cannot be null")
    private LocalDateTime startDateTime;
    @NotBlank(message = "the endDateTime cannot be blank")
    @NotNull(message="the endDateTime cannot be null")
    private LocalDateTime endDateTime;
    @NotBlank(message = "the name cannot be blank")
    @NotNull(message="the name cannot be null")
    private String name;
    @NotBlank(message = "the description cannot be blank")
    @NotNull(message="the description cannot be null")
    @Size(min = 6, message = "Description must be at least 6 characters long")
    private String description;
    @NotBlank(message = "the tags cannot be blank")
    @NotNull(message="the tags cannot be null")
    private List<Tag> tags;

}
