package com.example.DiningReview.model;

import com.example.DiningReview.enums.DinningReviewMark;
import com.example.DiningReview.enums.DinningReviewStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DINNING_REVIEWS")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class DinningReview {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="STATUS")
    private DinningReviewStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name="PEANUT_SCORE")
    private DinningReviewMark peanutScore;

    @Enumerated(EnumType.STRING)
    @Column(name="EGG_SCORE")
    private DinningReviewMark eggScore;

    @Enumerated(EnumType.STRING)
    @Column(name="DAIRY_SCORE")
    private DinningReviewMark dairyScore;

    @Column(name="COMMENTARY")
    private String commentary;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "USER_ID", nullable = false)
    @NonNull
    @JsonBackReference("user-reviews")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="RESTAURANT_ID", nullable = false)
    @NonNull
    @JsonBackReference("restaurant-reviews")
    private Restaurant restaurant;

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }

    public Long getRestaurantId() {
        return restaurant != null ? restaurant.getId() : null;
    }
}
