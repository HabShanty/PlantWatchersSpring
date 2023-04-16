package com.plantwatchersspring;

import com.plantwatchersspring.db.UserRepository;
import com.plantwatchersspring.db.PlantRepository;
import com.plantwatchersspring.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
            log.info("");

            Users user = repository.findUserById(1L);
            log.info(user.toString());
            log.info("");


        };
    }


}
