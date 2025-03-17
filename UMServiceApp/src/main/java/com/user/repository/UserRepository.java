package com.user.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmailOrEmployeeIdOrMobileOrUsername(String email, String employeeId, String mobile, String username);
    Page<User> findAll(Pageable pageable);
}