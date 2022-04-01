package com.grek.alcohol.user.repository;

import com.grek.alcohol.user.controller.UserDto;
import com.grek.alcohol.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> findUserById(Long id);
    UserDto saveUser(UserEntity userDto);
    void deleteUserById(Long id);
    void updateUser(UserDto userDto);
}
