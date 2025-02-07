package com.example.DiningReview.controller;


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
    public ResponseEntity<Object> getUserByName(@RequestBody User user) {
        Optional<User> foundedUser  = this.userRepository.findByName(user.getName());
        if (!foundedUser.isPresent()) {
            String errorMessage = " Failed to find user by name. ";
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundedUser, HttpStatus.FOUND);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteUser(@RequestParam Long id) {
         Optional<User> optionalUserForDelete = this.userRepository.findById(id);
        if (!optionalUserForDelete.isPresent()) {
            return new ResponseEntity<>(" Failed to found user by id ",HttpStatus.NOT_FOUND);
        }
        User userForDelete = optionalUserForDelete.get();
        try {
            this.userRepository.delete(userForDelete);
        }   catch (DataAccessException ex) {
            String errorMessage = " Failed to delete user by id. Error message: " + ex.getMessage();
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
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> optionalUserForUpdate = this.userRepository.findById(id);
        if (!optionalUserForUpdate.isPresent()) {
            return new ResponseEntity<>(" Failed to found user by id ",HttpStatus.NOT_FOUND);
        }
        User userForUpdate = optionalUserForUpdate.get();
        if (user.getName() != null) {
            userForUpdate.setName(user.getName());
        }
        if (user.getEmail() != null) {
            userForUpdate.setEmail(user.getEmail());
        }
        if (user.getCity() != null) {
            userForUpdate.setCity(user.getCity());
        }
        if (user.getState() != null) {
            userForUpdate.setState(user.getState());
        }
        if (user.getZipcode() != null) {
            userForUpdate.setZipcode(user.getZipcode());
        }
        if (user.getIsInterestedInPeanutAllergies() != null) {
            userForUpdate.setIsInterestedInPeanutAllergies(
                    user.getIsInterestedInPeanutAllergies()
            );
        }
        if (user.getIsInterestedInEggAllergies() != null) {
            userForUpdate.setIsInterestedInEggAllergies(
                    user.getIsInterestedInEggAllergies()
            );
        }
        if (user.getIsInterestedInDairyAllergies() != null) {
            userForUpdate.setIsInterestedInDairyAllergies(
                    user.getIsInterestedInDairyAllergies()
            );
        }
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
