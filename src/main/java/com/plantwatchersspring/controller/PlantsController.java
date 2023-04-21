package com.plantwatchersspring.controller;

import com.plantwatchersspring.entity.Plant;
import com.plantwatchersspring.entity.Users;
import com.plantwatchersspring.repository.PlantRepository;
import com.plantwatchersspring.repository.UserRepository;
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

    @Autowired
    UserRepository userRepo;

    /*
    @PostMapping("/plant")
    public ResponseEntity<Plant> save(@RequestBody Plant plant) {
        try {
            return new ResponseEntity<>(plantRepo.save(plant), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/
    //Example:
    //http://localhost:8080/plant/?userId=1&name=MyPlant&type=Flower
    @PostMapping("/plant/")
    public ResponseEntity<Plant> defaultSave(@RequestParam("userId") long userId,
                                             @RequestParam(name = "name", defaultValue = "Joe Dirt") String name,
                                             @RequestParam(name = "type", defaultValue = "Brushweed") String type) {
        try {
            Plant plant = new Plant(name, type, userId);
            return new ResponseEntity<>(plantRepo.save(plant), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Shows all plants
    @GetMapping("/plant")
    public ResponseEntity<List<Plant>> getAllPlants(){
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

    //Shows all plants of a specific user
    @GetMapping("/plant/user/{user_Id}")
    public ResponseEntity<List<Plant>> getAllPlantsOfUser(@PathVariable long user_Id){
        try{
            List<Plant> list = plantRepo.findAllByUserId(user_Id);
            if (list.isEmpty() || list.size() == 0){
                return new ResponseEntity<List<Plant>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Plant>>(list, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Shows a specific plant
    @GetMapping("/plant/{id}")
    public ResponseEntity<Plant> getsinglePlant(@PathVariable long id){

        Optional<Plant> plant = plantRepo.findById(id);
        if (plant.isPresent()) {
            return new ResponseEntity<Plant>(plant.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Plant>(HttpStatus.NOT_FOUND);

    }


    //Waters a specific plant
    @PutMapping("/plant/water/{id}")
    public ResponseEntity<Plant> waterPlantById(@PathVariable long id) {

            Optional<Plant> plant = plantRepo.findById(id);
            if (plant.isPresent()) {
                plant.get().addWater(15);
                return new ResponseEntity<Plant>(plantRepo.save(plant.get()), HttpStatus.OK);
            }
        return new ResponseEntity<Plant>(HttpStatus.NOT_FOUND);
    }

    //Feeds a specific plant to give it more maturity
    @PutMapping("/plant/feed/{id}")
    public ResponseEntity<Plant> feedPlantById(@PathVariable long id) {
        long water;
        long waterLoss = -5;
        //String response;

        Optional<Plant> plant = plantRepo.findById(id);
        if (plant.isPresent()) {
            water = plant.get().getWater();

            if(water == 100 ){
                plant.get().addMaturity(15);
                plant.get().addWater(waterLoss);
                //response = "Your plant is happy, healthy and growing fast.";
            }
            else if(water >= 75 ){
                plant.get().addMaturity(10);
                plant.get().addWater(waterLoss);
                //response = "Your plant is happy and doing well.";
            }
            else if(water >= 50 ){
                plant.get().addMaturity(5);
                plant.get().addWater(waterLoss);
                //response = "Your plant is doing good but could use more water.";
            }
            else if(water >= 25 ){
                plant.get().addMaturity(1);
                plant.get().addWater(waterLoss);
                //response = "Your plant is starving for water.";
            }
            else if(water <= 20 ){
                plant.get().addMaturity(0);
                plant.get().addWater(waterLoss);
                //response = "Your plant cant use this feed with so little water.";
            }

            return new ResponseEntity<Plant>(plantRepo.save(plant.get()), HttpStatus.OK);
        }
        return new ResponseEntity<Plant>(HttpStatus.NOT_FOUND);
    }

    //Harvests a specific plant to gain score for a user
    @PutMapping("/plant/harvest/{id}")
    public ResponseEntity<HttpStatus> harvestPlantById(@PathVariable long id) {
        long maturity;
        long user_Id;

        Optional<Plant> plant = plantRepo.findById(id);
        if (plant.isPresent()) {
            maturity = plant.get().getMaturity();
            user_Id = plant.get().getUserId();
            deletePlant(id);

            Optional<Users> user = userRepo.findById(user_Id);
            if (!user.isPresent()) {
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }
            user.get().setScore(maturity);
            userRepo.save(user.get());


            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }

    //Deletes a plant outright
    @DeleteMapping("/plant/{id}")
    public ResponseEntity<HttpStatus> deletePlant(@PathVariable Long id){
        try{
            Optional<Plant> plant = plantRepo.findById(id);
            if (plant.isPresent()){
                plantRepo.delete(plant.get());
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
