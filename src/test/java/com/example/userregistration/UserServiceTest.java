package com.example.userregistration;

import com.example.userregistration.enums.Status;
import com.example.userregistration.model.User;
import com.example.userregistration.repository.UserRepository;
import com.example.userregistration.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    private User user;

    @BeforeEach
    public void init(){
        user = new User("1", "John Doe", 25, "France", "john@example.com", null, Status.ACTIVE);
    }

    @Test
    public void testSaveUser() {
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("John Doe", savedUser.getName());
        assertEquals(25, savedUser.getAge());
        assertEquals("France", savedUser.getCountry());
        assertEquals("john@example.com", savedUser.getEmail());
        assertNull(savedUser.getPhoneNumber());
        assertEquals(Status.ACTIVE, savedUser.getStatus());
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserById("1");

        assertTrue(foundUser.isPresent());
        assertEquals("John Doe", foundUser.get().getName());
        assertEquals(25, foundUser.get().getAge());
        assertEquals("France", foundUser.get().getCountry());
        assertEquals("john@example.com", foundUser.get().getEmail());
        assertNull(foundUser.get().getPhoneNumber());
        assertEquals(Status.ACTIVE, foundUser.get().getStatus());
    }

    @Test
    public void testGetUsersByStatus() {
        List<User> users = Arrays.asList(
                user,
                new User("2", "Jane Doe", 30, "France", "jane@example.com", null, Status.ACTIVE)
        );
        when(userRepository.findByStatus(Status.ACTIVE)).thenReturn(users);

        List<User> foundUsers = userService.getUsersByStatus(Status.ACTIVE);

        assertEquals(2, foundUsers.size());
        assertEquals("John Doe", foundUsers.get(0).getName());
        assertEquals("Jane Doe", foundUsers.get(1).getName());
    }
}
