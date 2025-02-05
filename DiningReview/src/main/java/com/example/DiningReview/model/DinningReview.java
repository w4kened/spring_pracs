package com.example.DiningReview.model;

import com.example.DiningReview.enums.DinningReviewMark;
import com.example.DiningReview.enums.DinningReviewStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="REVIEWS")
@NoArgsConstructor
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
    @Column(name="DIARY_SCORY")
    private DinningReviewMark diaryScore;

    @Column(name="COMMENTARY")
    private String commentary;

    @Column(name="CREATED_AT")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="USER_ID", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

}
