package com.example.DiningReview.controller;


import com.example.DiningReview.model.DinningReview;
import com.example.DiningReview.model.User;
import com.example.DiningReview.repository.UserRepository;
import lombok.NonNull;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(
                (Collection<User>) this.userRepository.findAll()
        );
    }
    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @NonNull User user) {
        User newUser = new User();
        try {
            newUser = this.userRepository.save(user);
        } catch (DataAccessException ex) {
            String errorMessage = " Failed to save user. Error message: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @GetMapping("/findByName")
    public ResponseEntity<Object> getUserByName(@RequestParam String name) {
        Optional<User> foundUsers = this.userRepository.findByName(name);
        if (!foundUsers.isPresent()) {
            String errorMessage = " Failed to find user by name. ";
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundUsers, HttpStatus.FOUND);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam String name) {
        Optional<User> foundUsers  = this.userRepository.findByName(name);
        if (!foundUsers.isPresent()) {
            String errorMessage = " Failed to find user by name. ";
            return new ResponseEntity<>(errorMessage,HttpStatus.NOT_FOUND);
        }

        User userForDelete = foundUsers.get();
        try {
            this.userRepository.delete(userForDelete);
        }   catch (DataAccessException ex) {
            String errorMessage = " Failed to delete user by name. Error message: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(userForDelete, HttpStatus.OK);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Object> deleteAllUsers() {
        try {
            this.userRepository.deleteAll();
        } catch (DataAccessException ex) {
            String errorMessage = " Failed to delete all users. Error message: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestParam String name, @RequestBody User user) {
        Optional<User> optionalUserForUpdate = this.userRepository.findByName(name);
        if (!optionalUserForUpdate.isPresent()) {
            return new ResponseEntity<>(" Failed to found user by name ",HttpStatus.NOT_FOUND);
        }
        User userForUpdate = optionalUserForUpdate.get();

        Optional.ofNullable(user.getName())
                .ifPresent(userForUpdate::setName);
        Optional.ofNullable(user.getEmail())
                .ifPresent(userForUpdate::setEmail);
        Optional.ofNullable(user.getCity())
                .ifPresent(userForUpdate::setCity);
        Optional.ofNullable(user.getState())
                .ifPresent(userForUpdate::setState);
        Optional.ofNullable(user.getZipcode())
                .ifPresent(userForUpdate::setZipcode);
        Optional.ofNullable(user.getIsInterestedInPeanutAllergies())
                .ifPresent(userForUpdate::setIsInterestedInPeanutAllergies);
        Optional.ofNullable(user.getIsInterestedInEggAllergies())
                .ifPresent(userForUpdate::setIsInterestedInEggAllergies);
        Optional.ofNullable(user.getIsInterestedInDairyAllergies())
                .ifPresent(userForUpdate::setIsInterestedInDairyAllergies);

        User updatedUser = new User();
        try {
            updatedUser = this.userRepository.save(userForUpdate);
        } catch (DataAccessException ex) {
            String errorMessage = " Failed to update user by id with request body, Error message: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
