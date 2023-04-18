package com.plantwatchersspring.controller;

import com.plantwatchersspring.entity.Plant;
import com.plantwatchersspring.entity.Users;
import com.plantwatchersspring.repository.PlantRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlantsController {

    @Autowired
    PlantRepository plantRepo;

    @PostMapping("/plant")
    public ResponseEntity<Plant> save(@RequestBody Plant plant) {
        try {
            return new ResponseEntity<>(plantRepo.save(plant), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/plant")
    public ResponseEntity<List<Plant>> getAllUsers(){
        try{
            List<Plant> list = plantRepo.findAll();
            if (list.isEmpty() || list.size() == 0){
                return new ResponseEntity<List<Plant>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Plant>>(list, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
