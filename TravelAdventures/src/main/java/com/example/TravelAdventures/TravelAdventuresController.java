package com.example.TravelAdventures;

import com.example.TravelAdventures.Adventure;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.regex.*;



@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

    private final AdventureRepository adventureRepository;

    public TravelAdventuresController(AdventureRepository adventureRepo) {
        this.adventureRepository = adventureRepo;
    }

    // Add controller methods below:
    @GetMapping
    public Iterable<Adventure> findAllAdventures() {
        return adventureRepository.findAll();
    }

    @PostMapping()
    public String addAdventure(@RequestBody Adventure adventure) {
        if (adventure.getId() != null) {
            Optional<Adventure> adventureOptional = this.adventureRepository.findById(adventure.getId());

            if (adventureOptional.isPresent()) {
//                System.out.println();
                throw new ResponseStatusException(HttpStatus.CONFLICT, "duplicating record <<<");
            }
        }
        if (!this.validateDate(adventure.getDate()) || !this.validateNumPhotos(adventure.getNumPhotos())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "validation error! <<<");
        }
        System.out.println("adv "+adventure.toString());
        adventureRepository.save(adventure);
        return "adventure added!";
    }

    private boolean validateDate(String date) {
        Pattern pattern = Pattern.compile("\\d\\d/\\d\\d/\\d\\d\\d\\d");
        Matcher matcher = pattern.matcher(date);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    private  boolean validateNumPhotos(Long num) {
        if (num < 0) {
            return false;
        }
        return true;
    }

    @GetMapping("/bycountry")
    public Iterable<Adventure> findAdventureByCountry(@RequestParam String country) {
        return adventureRepository.findByCountry(country);
    }
    @GetMapping("/bystate")
    public Iterable<Adventure> findAdventureByState(@RequestParam String state) {
        return adventureRepository.findByState(state);
    }
    @PutMapping("/{id}")
    public Adventure updateAdventureById(@PathVariable Integer id, @RequestBody Adventure adventure) {
        Optional<Adventure> adventureOptional = this.adventureRepository.findById(id);

        if (!adventureOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "record is not present <<<");
        }
        Adventure existingAdventure = adventureOptional.get();

        if (adventure.getDate() != null) {
            existingAdventure.setDate(adventure.getDate());
        }
        if (adventure.getCountry() != null) {
            existingAdventure.setCountry(adventure.getCountry());
        }
        if (adventure.getCity() != null) {
            existingAdventure.setCity(adventure.getCity());
        }
        if (adventure.getState() != null) {
            existingAdventure.setState(adventure.getState());
        }
        if (adventure.getNumPhotos() != null) {
            existingAdventure.setNumPhotos(adventure.getNumPhotos());
        }
        if (adventure.getBlogCompleted() != null) {
            existingAdventure.setBlogCompleted(adventure.getBlogCompleted());
        }

        return this.adventureRepository.save(existingAdventure);
    }

    @DeleteMapping("/{id}")
    public String deleteAdventureById(@PathVariable Integer id) {
        Optional<Adventure> adventureOptional = adventureRepository.findById(id);
        if (!adventureOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "record is not present <<<");
        }
        this.adventureRepository.deleteById(id);
        return "Successfully deleted record by id "+id;
    }




}
