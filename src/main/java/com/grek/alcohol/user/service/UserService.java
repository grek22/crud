package com.grek.alcohol.user.service;

import com.grek.alcohol.user.controller.UserDto;
import com.grek.alcohol.user.entity.UserEntity;
import com.grek.alcohol.user.exception.UserNotFoundException;
import com.grek.alcohol.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(String.format("User not exists, id: %d", id)));
    }

    public UserDto saveUser(UserDto userDto) {
        return userRepository.saveUser(UserEntity.fromDomain(userDto));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteUserById(id);
    }

    public void updateUser(UserDto userDto) {
        userRepository.updateUser(userDto);
    }
}
