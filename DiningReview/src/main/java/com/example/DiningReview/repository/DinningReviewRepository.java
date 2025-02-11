package com.example.DiningReview.repository;

import com.example.DiningReview.enums.DinningReviewStatus;
import com.example.DiningReview.model.DinningReview;
import com.example.DiningReview.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DinningReviewRepository extends CrudRepository<DinningReview, Long> {
    List<DinningReview> findByStatus(DinningReviewStatus dinningReviewStatus);
    List<DinningReview> findByStatusAndRestaurant(DinningReviewStatus dinningReviewStatus, Restaurant restaurant);
//    List<DinningReview>
}

