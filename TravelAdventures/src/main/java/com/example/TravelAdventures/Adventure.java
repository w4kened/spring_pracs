package com.example.TravelAdventures;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Table(name = "ADVENTURES")
public class Adventure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DATE")
    private String date;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "NUM_PHOTOS")
    private Long numPhotos;

    @Column(name = "BLOG_COMPLETED")
    private Boolean blogCompleted;

    public Integer getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public Long getNumPhotos() {
        return this.numPhotos;
    }

    public Boolean getBlogCompleted() {
        return this.blogCompleted;
    }

    public void setBlogCompleted(Boolean blogCompleted) {
        this.blogCompleted = blogCompleted;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setNumPhotos(Long numPhotos) {
        this.numPhotos = numPhotos;
    }

    @Override
    public String toString() {
        return "Adventure{" + "id=" + id + ", date='" + date + '\'' + ", country='" + country + '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", numPhotos=" + numPhotos + ", blogCompleted=" + blogCompleted + '}';
    }
}
