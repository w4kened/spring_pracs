package com.example.DiningReview.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USERS")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    Long id;

    @Column(name="NAME", unique = true)
    @NonNull
    String name;

    @Column(name="EMAIL")
    @NonNull
    String email;

    @Column(name="CITY")
    @NonNull
    String city;

    @Column(name="STATE")
    String state;

    @Column(name="ZIP_CODE")
    String zipcode;

    @Column(name="PEANUT_ALLERGIES_INTERESTED")
    @NonNull
    Boolean isInterestedInPeanutAllergies;

    @Column(name="EGG_ALLERGIES_INTERESTED")
    @NonNull
    Boolean isInterestedInEggAllergies;

    @Column(name="DAIRY_ALLERGIES_INTERESTED")
    @NonNull
    Boolean isInterestedInDairyAllergies;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<DinningReview> reviews;

    public User(String name,
                String email,
                String city,
                Boolean isInterestedInPeanutAllergies,
                Boolean isInterestedInEggAllergies,
                Boolean isInterestedInDairyAllergies) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.zipcode = "";
        this.state = "";
        this.reviews = new HashSet<>();
        this.isInterestedInPeanutAllergies = isInterestedInPeanutAllergies;
        this.isInterestedInEggAllergies = isInterestedInEggAllergies;
        this.isInterestedInDairyAllergies = isInterestedInDairyAllergies;
    }
}
