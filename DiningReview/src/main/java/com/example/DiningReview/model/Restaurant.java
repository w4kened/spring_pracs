package com.example.DiningReview.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="RESTAURANTS")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="NAME", unique = true)
    @NonNull
    @JsonProperty
    private String name;

    @Column(name="PEANUT_SCORE")
    private Double peanutScore;

    @Column(name="EGG_SCORE")
    private Double eggScore;

    @Column(name="DAIRY_SCORE")
    private Double dairyScore;

    @Column(name="ZIP_CODE", unique = true)
    @JsonProperty
    private String zipcode;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("restaurant-reviews")
    private Set<DinningReview> reviews;
}
