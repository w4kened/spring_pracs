package com.example.DiningReview.controller;

import com.example.DiningReview.model.DinningReview;
import com.example.DiningReview.model.Restaurant;
import com.example.DiningReview.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return new ArrayList<>(
                (Collection<Restaurant>) this.restaurantRepository.findAll()
        );
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> getRestaurant(@PathVariable("id") Long restaurantId) {
        Restaurant foundRestaurants = this.restaurantRepository.findById(restaurantId).get();
        return new ResponseEntity<>(foundRestaurants, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Object> saveRestaurant(@RequestBody @NonNull Restaurant restaurant) {
        Restaurant newRestaurant = new Restaurant();
        try {
            newRestaurant = this.restaurantRepository.save(restaurant);
        } catch (DataAccessException ex) {
            String errorMessage = " Failed to save restaurant. Error message: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newRestaurant, HttpStatus.CREATED);
    }
    @GetMapping("/findByZipCodeWithSubmittedAllergies")
    public List<Restaurant> getRestaurantByZipCodeWithSubmittedReviews(@RequestParam String zipcode) {
        List<Restaurant> foundRestaurants = new ArrayList<>();
        List<Restaurant> filteredRestaurants = new ArrayList<>();

        try {
            foundRestaurants = this.restaurantRepository.findByZipcode(zipcode);
            for (Restaurant restaurant : foundRestaurants) {
                Set<DinningReview> dinningReviews = restaurant.getReviews();
                for (DinningReview dinningReview : dinningReviews) {
                    if (dinningReview.getPeanutScore() != null ||
                        dinningReview.getEggScore() != null ||
                        dinningReview.getDairyScore() != null) {
                        filteredRestaurants.add(restaurant);
                    }
                }
            }
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        Collections.sort(filteredRestaurants, Collections.reverseOrder());
        return filteredRestaurants;
    }

}
