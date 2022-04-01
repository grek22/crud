package com.grek.alcohol.user.repository;

import com.grek.alcohol.user.controller.UserDto;
import com.grek.alcohol.user.entity.UserEntity;
import com.grek.alcohol.user.exception.EmailAlreadyInUseException;
import com.grek.alcohol.user.exception.UserNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<UserDto> findUserById(Long id) {
        return userJpaRepository.findById(id)
                .map(UserDto::from);
    }

    @Override
    @Transactional
    public UserDto saveUser(UserEntity user) {
        userJpaRepository.findByEmail(user.getEmail()).ifPresent(value -> {
            throw new EmailAlreadyInUseException(String.format("Email already exists: %s", user.getEmail()));
        });
        return UserDto.from(userJpaRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userJpaRepository.findById(id).ifPresentOrElse(
                user -> userJpaRepository.deleteById(id),
                () -> {
                    throw new UserNotFoundException(String.format("User not found exception, id: %s", id));
                }
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUser(UserDto userDto) {
        var userOptional = userJpaRepository.findById(userDto.getId());
        userOptional.ifPresentOrElse(user -> updateFromDomain(user, userDto), () -> saveUser(UserEntity.fromDomain(userDto)));
    }

    //metodę można zabiezpieczyć na wypadek próby zaktualizowania maila na już obecny, i tak się to nie wydarzy z powodu ograniczeń na bazie ale chodzi o złpanie wyjątku
    private void updateFromDomain(UserEntity user, UserDto userDto) {
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
    }
}
