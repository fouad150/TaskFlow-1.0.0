package com.example.taskflow.DTO;

import com.example.taskflow.entity.Role;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
        @NotBlank(message = "Username cannot be blank")
        @NotNull(message = "Username cannot be null")
        private String userName;

        @NotBlank(message = "Email cannot be blank")
        @NotNull(message="email cannot be null")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Password cannot be blank")
        @NotNull(message="password cannot be null")
        @Size(min = 6, message = "Password must be at least 6 characters long")
        private String password;
    }
