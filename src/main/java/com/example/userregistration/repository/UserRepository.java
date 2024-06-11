package com.example.userregistration.repository;

import com.example.userregistration.enums.Status;
import com.example.userregistration.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByStatus(Status status);
}
