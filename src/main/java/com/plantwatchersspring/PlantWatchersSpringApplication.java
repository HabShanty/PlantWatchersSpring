package com.plantwatchersspring;

import com.plantwatchersspring.repository.UserRepository;
import com.plantwatchersspring.entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.plantwatchersspring.repository", "com.plantwatchersspring.entity", "com.plantwatchersspring.controller"})
@SpringBootApplication
public class PlantWatchersSpringApplication {

    //root resource at http://localhost:8080/

    private static final Logger log =
            LoggerFactory.getLogger(PlantWatchersSpringApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(PlantWatchersSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(UserRepository repository){
        return (args) -> {

            repository.save(new Users(0,"OGgardener", "fakeEmail@bananas.com", "hashedPW"));
            repository.save(new Users(0,"Sam Gamgee", "fakeEmail@bananas.com", "hashedPW"));
            repository.save(new Users(0,"Nosy Neighbor", "fakeEmail@bananas.com", "hashedPW"));
            repository.save(new Users(0,"Farmin' Joe", "fakeEmail@bananas.com", "hashedPW"));

            log.info("Showing all farmers");
            for (Users user : repository.findAll()) {
                log.info(user.toString());
            }
            log.info("Setup completed.");
        };
    }
}
