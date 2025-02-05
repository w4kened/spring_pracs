package com.example.DiningReview.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="RESTAURANTS")
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="NAME")
    private String name;

    @Column(name="PEANUT_SCORE")
    private Double peanutScore;

    @Column(name="EGG_SCORE")
    private Double eggScore;

    @Column(name="DAIRY_SCORE")
    private Double dairyScore;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DinningReview> reviews;
}
