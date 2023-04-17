package com.plantwatchersspring.controller;

import com.plantwatchersspring.entity.Users;
import com.plantwatchersspring.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
