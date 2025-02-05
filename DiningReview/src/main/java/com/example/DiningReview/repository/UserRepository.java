package com.example.DiningReview.repository;
import com.example.DiningReview.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByName(String name);
    Boolean existsByName(String name);
}


