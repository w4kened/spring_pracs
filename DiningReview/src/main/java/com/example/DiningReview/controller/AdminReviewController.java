package com.example.DiningReview.controller;

import com.example.DiningReview.enums.DinningReviewStatus;
import com.example.DiningReview.model.DinningReview;
import com.example.DiningReview.repository.DinningReviewRepository;
import com.example.DiningReview.repository.RestaurantRepository;
import com.example.DiningReview.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admin")
public class AdminReviewController {
//    public boolean isApprovedByAdmin;
    private final DinningReviewRepository dinningReviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping("/findPending")
    public List<DinningReview> getPendingReviews() {
        return this.dinningReviewRepository.findByStatus(DinningReviewStatus.PENDING);
    }
    @PostMapping("/approve/{id}")
    public ResponseEntity<Object> approveDinningReview(@PathVariable("id") Long dinningReviewId) {
        List<DinningReview> pendingReviews = this.getPendingReviews();
        for (DinningReview review : pendingReviews) {
            if (review.getId().equals(dinningReviewId)) {
                review.setStatus(DinningReviewStatus.APPROVED);
                try {
                    this.dinningReviewRepository.save(review);
                } catch (DataAccessException ex) {
                    String errorMessage = " Failed to approve dinning review. Error message: " + ex.getMessage();
                    return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return new ResponseEntity<>(review, HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(" Failed to approve dinning review. ", HttpStatus.NOT_FOUND);
    }
    @PostMapping("/reject/{id}")
    public ResponseEntity<Object> rejectDinningReview(@PathVariable("id") Long dinningReviewId) {
        List<DinningReview> pendingReviews = this.getPendingReviews();
        for (DinningReview review : pendingReviews) {
            if (review.getId().equals(dinningReviewId)) {
                review.setStatus(DinningReviewStatus.REJECTED);
                try {
                    this.dinningReviewRepository.save(review);
                } catch (DataAccessException ex) {
                    String errorMessage = " Failed to reject dinning review. Error message: " + ex.getMessage();
                    return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return new ResponseEntity<>(review, HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(" Failed to reject dinning review. ", HttpStatus.NOT_FOUND);
    }
}
