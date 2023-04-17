package com.plantwatchersspring.controller;

import com.plantwatchersspring.entity.Users;
import com.plantwatchersspring.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    UserRepository usersRepo;

    @PostMapping("/users")
    public ResponseEntity<Users> save(@RequestBody Users users) {
        try {
            return new ResponseEntity<>(usersRepo.save(users), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers(){
        try{
            List<Users> list = usersRepo.findAll();
            if (list.isEmpty() || list.size() == 0){
                return new ResponseEntity<List<Users>>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<List<Users>>(list, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getSingleUser(@PathVariable long id){
        Optional<Users> user = usersRepo.findById(id);

        if(user.isPresent()){
            return new ResponseEntity<Users>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@RequestBody Users users){
        try{
            return new ResponseEntity<Users>(usersRepo.save(users), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id){
        try{
            Optional<Users> users = usersRepo.findById(id);
            if (users.isPresent()){
                usersRepo.delete(users.get());
            }
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
