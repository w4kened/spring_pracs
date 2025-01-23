package com.example.SuperHero;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
public interface SuperHeroRepository extends CrudRepository<SuperHero, Long> {

    List<SuperHero> findByLastName(String lastName);

    SuperHero findById(long id);
}
