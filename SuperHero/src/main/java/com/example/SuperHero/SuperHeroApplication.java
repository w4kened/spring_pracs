package com.example.SuperHero;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SuperHeroApplication {
	private static final Logger log = LoggerFactory.getLogger(SuperHeroApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SuperHeroApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(SuperHeroRepository repository, SuperReportRepository repository2) {
		return (args) -> {

			// save a few customers
			repository.save(new SuperHero("Fran", "Fling", "Flying"));
			repository.save(new SuperHero("Wendy", "Welsh", "Water Bending"));
			repository.save(new SuperHero("Mary", "Macky", "Mind Reading"));
			repository.save(new SuperHero("Ingrid", "Immerson", "Invisibility"));

			repository2.save(new SuperReport("64672", "123 Main St", "2020-05-04"));
			repository2.save(new SuperReport("31008", "674 Broad St", "2018-08-12"));
			repository2.save(new SuperReport("64672", "987 East Dr", "2020-09-08"));
			repository2.save(new SuperReport("64672", "546 South Blvd", "2020-12-29"));
			repository2.save(new SuperReport("31008", "712 Early St", "2021-01-03"));


			// fetch all customers
			log.info("Super Heroes found with findAll():");
			log.info("-------------------------------");
			for (SuperHero superHero : repository.findAll()) {
				log.info(superHero.toString());
			}
			log.info("Super Reports found with findAll():");
			log.info("-------------------------------");
			for (SuperReport superReport : repository2.findAll()) {
				log.info(superReport.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			SuperHero superHero = repository.findById(1L);
			log.info("SuperHero found with findById(1L):");
			log.info("--------------------------------");
			log.info(superHero.toString());
			log.info("");

			// fetch customers by last name
			log.info("Super Hero found with findByLastName('Immerson'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Immerson").forEach(Immerson -> {
				log.info(Immerson.toString());
			});

			log.info("Super Report found with findByPostalCode('31008'):");
			log.info("--------------------------------------------");
			//repository2.findByPostalCode("31008").forEach(31008 -> {
			//   log.info(31008.toString());
			// });
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			//  log.info(bauer.toString());
			// }
			log.info("");
		};
	}

}
