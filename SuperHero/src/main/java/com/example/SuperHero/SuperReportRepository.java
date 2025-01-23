package com.example.SuperHero;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SuperReportRepository extends CrudRepository<SuperReport, Long> {
    List<SuperReport> findByPostalCode(String postalCode);
    SuperReport findById(long id);
}
