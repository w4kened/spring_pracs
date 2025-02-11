package com.example.DiningReview.controller;

import com.example.DiningReview.enums.DinningReviewMark;
import com.example.DiningReview.enums.DinningReviewStatus;
import com.example.DiningReview.model.DinningReview;
import com.example.DiningReview.model.Restaurant;
import com.example.DiningReview.model.User;
import com.example.DiningReview.repository.DinningReviewRepository;
import com.example.DiningReview.repository.RestaurantRepository;
import com.example.DiningReview.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/dinningReviews")
public class DinningReviewController {
    private final DinningReviewRepository dinningReviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping
    public List<DinningReview> getAllDinningReviews() {
        return new ArrayList<>(
                (Collection<DinningReview>) this.dinningReviewRepository.findAll()
        );
    }
    //validate submitting dinning review
    @PostMapping
    public ResponseEntity<Object> saveDinningReview(@RequestBody DinningReview dinningReview) {
        Long userId = dinningReview.getUser().getId();
        Long restaurantId = dinningReview.getRestaurant().getId();
        Optional<User> optionalUser = this.userRepository.findById(userId);
        Optional<Restaurant> optionalRestaurant = this.restaurantRepository.findById(restaurantId);
        DinningReview newDinningReview = new DinningReview();

        if (!optionalUser.isPresent()) {
            return new ResponseEntity<>(" Failed to submit review with given user id",
                    HttpStatus.NOT_FOUND);
        }
        if (!optionalRestaurant.isPresent()) {
            return new ResponseEntity<>(" Failed to submit review with given restaurant id",
                    HttpStatus.NOT_FOUND);
        }
        if (dinningReview.getPeanutScore() != null) {
            newDinningReview.setPeanutScore(dinningReview.getPeanutScore());
            updateRestaurantScore(optionalRestaurant.get(), dinningReview.getPeanutScore(), "peanut");
        }
        if (dinningReview.getEggScore() != null) {
            newDinningReview.setEggScore(dinningReview.getEggScore());
            updateRestaurantScore(optionalRestaurant.get(), dinningReview.getEggScore(), "egg");
        }
        if (dinningReview.getDairyScore() != null) {
            newDinningReview.setDairyScore(dinningReview.getDairyScore());
            updateRestaurantScore(optionalRestaurant.get(), dinningReview.getDairyScore(), "dairy");
        }
        Optional.ofNullable(dinningReview.getCommentary())
                .ifPresent(newDinningReview::setCommentary);
        newDinningReview.setUser(optionalUser.get());
        newDinningReview.setRestaurant(optionalRestaurant.get());
        newDinningReview.setStatus(DinningReviewStatus.PENDING);
        newDinningReview.setCreatedAt(LocalDateTime.now());

        try {
            newDinningReview = this.dinningReviewRepository.save(newDinningReview);
        } catch (DataAccessException ex) {
            String errorMessage = " Failed to save dinning review. Error message: " + ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(newDinningReview, HttpStatus.OK);
    }


    @GetMapping("/findApprovedReviewsForRestaurant/{id}")
    public List<DinningReview> getApprovedReviews(@PathVariable("id") Long restaurantId) {
        try {
            return this.dinningReviewRepository.findByStatusAndRestaurant(DinningReviewStatus.APPROVED, this.restaurantRepository.findById(restaurantId).get());
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
    private static void updateRestaurantScore(Restaurant restaurant, DinningReviewMark score, String type) {
        Double currentScore = switch (type) {
            case "peanut" -> restaurant.getPeanutScore();
            case "egg" -> restaurant.getEggScore();
            case "dairy" -> restaurant.getDairyScore();
            default -> null;
        };
        Double updatedScore = currentScore != null
                ? (currentScore + score.getValue()) / 2
                : score.getValue();

        switch (type) {
            case "peanut" -> restaurant.setPeanutScore(updatedScore);
            case "egg" -> restaurant.setEggScore(updatedScore);
            case "dairy" -> restaurant.setDairyScore(updatedScore);
        }
    }
}
