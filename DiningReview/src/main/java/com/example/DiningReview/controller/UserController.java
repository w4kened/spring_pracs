package com.example.DiningReview.controller;


import com.example.DiningReview.dto.UserDto;
import com.example.DiningReview.model.DinningReview;
import com.example.DiningReview.model.User;
import com.example.DiningReview.repository.UserRepository;
import lombok.NonNull;
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
        return new ArrayList<>((Collection<User>) this.userRepository.findAll());
    }
    @PostMapping
    public User saveUser(@RequestBody @NonNull User user) {
        User newUser = this.userRepository.save(user);
        return newUser;
    }


}
