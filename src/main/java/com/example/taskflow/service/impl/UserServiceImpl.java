package com.example.taskflow.service.impl;

import com.example.taskflow.entity.User;
import com.example.taskflow.exception.AlreadyExistsException;
import com.example.taskflow.exception.DoesNoExistException;
import com.example.taskflow.repository.UserRepository;
import com.example.taskflow.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User register(User user){
        Optional<User> userByUserName= userRepository.findByUserName(user.getUserName());
        if(userByUserName.isPresent()){
            throw new AlreadyExistsException("this username is already exists try another");
        }else{
            Optional<User> userByEmail=userRepository.findByEmail(user.getEmail());
            if(userByEmail.isPresent()){
                throw new AlreadyExistsException("this email is already exists");
            }else{
                return userRepository.save(user);
            }

        }
    }

    @Override
    public User login(User user){
        Optional<User> userByEmailAndPassword=userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(userByEmailAndPassword.isEmpty()){
           throw new DoesNoExistException("the information are not correct");
        }
        return userByEmailAndPassword.get();
    }

    @Override
    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }
}
