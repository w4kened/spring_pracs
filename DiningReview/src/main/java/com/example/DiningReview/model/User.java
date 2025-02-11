package com.example.DiningReview.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name="USERS")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="NAME", unique = true)
    @NonNull
    @JsonProperty
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Column(name="CITY")
    private String city;

    @Column(name="STATE")
    private String state;

    @Column(name="ZIP_CODE")
    private String zipcode;

    @Column(name="PEANUT_ALLERGIES_INTERESTED")
    private Boolean isInterestedInPeanutAllergies;

    @Column(name="EGG_ALLERGIES_INTERESTED")
    private Boolean isInterestedInEggAllergies;

    @Column(name="DAIRY_ALLERGIES_INTERESTED")
    private Boolean isInterestedInDairyAllergies;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("user-reviews")
    private Set<DinningReview> reviews;
}
