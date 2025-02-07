package com.example.DiningReview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
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
    Long id;

    @Column(name="NAME", unique = true)
    @NonNull
    @JsonProperty
    String name;

    @Column(name="EMAIL")
    String email;

    @Column(name="CITY")
    String city;

    @Column(name="STATE")
    String state;

    @Column(name="ZIP_CODE")
    String zipcode;

    @Column(name="PEANUT_ALLERGIES_INTERESTED")
    Boolean isInterestedInPeanutAllergies;

    @Column(name="EGG_ALLERGIES_INTERESTED")
    Boolean isInterestedInEggAllergies;

    @Column(name="DAIRY_ALLERGIES_INTERESTED")
    Boolean isInterestedInDairyAllergies;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    Set<DinningReview> reviews;


}
