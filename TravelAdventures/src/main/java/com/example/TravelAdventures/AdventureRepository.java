package com.example.TravelAdventures;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.TravelAdventures.Adventure;

public interface AdventureRepository extends CrudRepository<Adventure, Integer> {
    public List<Adventure> findByCountry(String country);
    public List<Adventure> findByState(String state);
    public List<Adventure> findById(Long Id);
}