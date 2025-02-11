package com.example.DiningReview.repository;

import com.example.DiningReview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findByZipcode(String zipcode);
}
