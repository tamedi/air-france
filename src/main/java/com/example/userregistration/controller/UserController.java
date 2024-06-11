package com.example.userregistration.controller;


import com.example.userregistration.enums.Status;
import com.example.userregistration.model.User;
import com.example.userregistration.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * Create a new user.
     *
     * @param user the user to create
     * @return the created user
     */
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the user ID
     * @return the user if found, 404 Not Found otherwise
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Retrieves users by status.
     *
     * @param status the status of users to retrieve, defaults to "ACTIVE"
     * @return the list of users with the specified status
     */
    @GetMapping
    public ResponseEntity<List<User>> getUsersByStatus(@RequestParam(defaultValue = "ACTIVE") Status status) {
        List<User> users = userService.getUsersByStatus(status);
        return ResponseEntity.ok(users);
    }
}
