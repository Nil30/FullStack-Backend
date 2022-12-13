package com.codewithnilesh.FullStackBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codewithnilesh.FullStackBackend.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
