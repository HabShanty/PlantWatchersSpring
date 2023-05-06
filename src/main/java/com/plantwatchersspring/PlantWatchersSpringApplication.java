package com.plantwatchersspring;

import com.plantwatchersspring.entity.Plant;
import com.plantwatchersspring.entity.Users;
import com.plantwatchersspring.repository.PlantRepository;
import com.plantwatchersspring.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@ComponentScan({"com.plantwatchersspring.repository", "com.plantwatchersspring.entity", "com.plantwatchersspring.controller"})
@SpringBootApplication
@RestController
public class PlantWatchersSpringApplication {

    //root resource at http://localhost:8080/
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PlantRepository plantRepo;

    private static final Logger log =
            LoggerFactory.getLogger(PlantWatchersSpringApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(PlantWatchersSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            Users user1 = new Users("OGGardener", "FakeMail@Bananas.org", "hashedPW");
            Users user2 = new Users("Sam Gamgee", "TotallyRealEmail@fakeEmail.com", "cleartext");
            Users user3 = new Users("Farmin' Joe", "MiddleOfNowhere@cows.com", "DarnFanglin");


            Plant plant1 = new Plant("Basil", "Herb", 1);
            Plant plant2 = new Plant("Rosemary", "Herb", 1);
            Plant plant3 = new Plant("Tomato", "Fruit", 2);

            userRepo.save(user1);
            userRepo.save(user2);
            userRepo.save(user3);

            plantRepo.save(plant1);
            plantRepo.save(plant2);
            plantRepo.save(plant3);

            // Retrieve users and plants from the database
            List<Users> users = userRepo.findAll();
            List<Plant> plants = plantRepo.findAll();

            // Print out the users and plants
            System.out.println("Users:");
            users.forEach(System.out::println);

            System.out.println("Plants:");
            plants.forEach(System.out::println);
        };
    }
}
