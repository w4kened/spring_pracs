package com.example.SuperHero;


import java.util.List;
import java.lang.Iterable;
import java.util.Date;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/superHeroes")
public class SuperHeroController {

    private final SuperHeroRepository superHeroRepository;
    private final SuperReportRepository superReportRepository;

    public SuperHeroController(SuperHeroRepository superHeroRepository, SuperReportRepository superReportRepository) {
        this.superHeroRepository = superHeroRepository;
        this.superReportRepository = superReportRepository;
    }
    @GetMapping()
    public Iterable<SuperHero> getSuperHeros() {
        Iterable<SuperHero> superHeroes = superHeroRepository.findAll();
        return superHeroes;
    }
    @PostMapping("/addNew")
    public String createNewSuperHero(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String superPower) {
        SuperHero newSuperHero = new SuperHero(firstName, lastName, superPower);
        superHeroRepository.save(newSuperHero);
        return "New Super Hero successfully added!";
    }
    @PostMapping("/help")
    public String postHelp(@RequestParam String postalCode, @RequestParam String streetAddress) {
        System.out.println(">> "+postalCode+" "+streetAddress);
        SuperReport newSuperReport = new SuperReport(postalCode, streetAddress, "");
        superReportRepository.save(newSuperReport);
        return "Thanks! Super Heroes have been dispatched to your location!";
    }
    @GetMapping("/heroReport")
    public Iterable<SuperReport> getHeroReport() {
        System.out.println("before invoking to repositorty");
        Iterable<SuperReport> superReport = superReportRepository.findAll();
        System.out.println("after invoking to repositorty");

        return superReport;
    }
    @PostMapping("/{postalCode}")
    public Iterable<SuperReport> getHeroReportByPostal(@PathVariable String postalCode) {
        Iterable<SuperReport> superReport = superReportRepository.findByPostalCode(postalCode);
        return superReport;
    }
}