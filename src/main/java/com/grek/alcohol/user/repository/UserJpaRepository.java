package com.grek.alcohol.user.repository;

import com.grek.alcohol.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<UserEntity> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<UserEntity> findByEmail(String email);

    void deleteById(Long id);
}
