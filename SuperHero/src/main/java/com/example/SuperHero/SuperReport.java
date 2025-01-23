package com.example.SuperHero;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SuperReport {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String postalCode;
    private String streetAddress;
    private String completed;

    protected SuperReport() {}

    public SuperReport(String postalCode, String streetAddress, String completed) {
        this.postalCode = postalCode;
        this.streetAddress = streetAddress;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return String.format(
                "Super Hero[id=%d, postalCode='%s', streetAddress='%s', completed='%s']",
                id, postalCode, streetAddress, completed);
    }

    public Long getId() {
        return id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCompleted(){
        return completed;
    }
}