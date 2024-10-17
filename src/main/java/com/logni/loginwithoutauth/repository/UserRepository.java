package com.logni.loginwithoutauth.repository;

import com.logni.loginwithoutauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUserEmail(String email);
}
