package com.example.taskflow.convertor;

import com.example.taskflow.DTO.UserDTO;
import com.example.taskflow.DTO.UserResponseDTO;
import com.example.taskflow.entity.Role;
import com.example.taskflow.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {
    public static User convertToEntity(UserDTO userDTO) {
       User user=new User();
       user.setUserName(userDTO.getUserName());
       user.setEmail(userDTO.getEmail());
       user.setPassword(userDTO.getPassword());
       user.setRole(new Role(2L,"employee"));
       return user;
    }

    public static UserResponseDTO convertToDTO(User user) {
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    public static List<UserResponseDTO> convertToDTOList(List<User> users) {
        return users.stream()
                .map(user -> UserConverter.convertToDTO(user))
                .collect(Collectors.toList());
    }
}
