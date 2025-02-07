package com.example.DiningReview.repository;
import com.example.DiningReview.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String name);
    Boolean existsByName(String name);
}


