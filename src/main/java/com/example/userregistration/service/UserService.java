package com.example.userregistration.service;



import com.example.userregistration.enums.Status;
import com.example.userregistration.model.User;
import com.example.userregistration.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    /**
     * Saves a new user.
     *
     * @param user the user to save
     * @return the saved user
     */

    public User saveUser(User user) {
        long startTime = System.currentTimeMillis();
        User createdUser = userRepository.save(user);
        long endTime = System.currentTimeMillis();
        logger.info("Created user: {}", createdUser);
        logger.info("Processing time for saveUser: {} ms", (endTime - startTime));
        return createdUser;
    }



    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return the user if found, empty otherwise
     */
    public Optional<User> getUserById(String id) {
        long startTime = System.currentTimeMillis();
        Optional<User> user = userRepository.findById(id);
        long endTime = System.currentTimeMillis();
        user.ifPresentOrElse(
                foundUser -> {
                    logger.info("Found user: {}", foundUser);
                    logger.info("Processing time for getUserById: {} ms", (endTime - startTime));
                },
                () -> {
                    logger.info("User with ID {} not found", id);
                    logger.info("Processing time for getUserById: {} ms", (endTime - startTime));
                }
        );
        return user;
    }



    /**
     * Retrieves users by status.
     *
     * @param status the status of users to retrieve
     * @return the list of users with the specified status
     */
    public List<User> getUsersByStatus(Status status) {
        long startTime = System.currentTimeMillis();
        List<User> users = userRepository.findByStatus(status);
        long endTime = System.currentTimeMillis();
        logger.info("Found users with status {}: {}", status, users);
        logger.info("Processing time for getUsersByStatus: {} ms", (endTime - startTime));
        return users;
    }


}

