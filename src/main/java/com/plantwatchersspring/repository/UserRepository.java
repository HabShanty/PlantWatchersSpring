package com.plantwatchersspring.repository;

import java.util.List;

import com.plantwatchersspring.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(collectionResourceRel = "users", path = "users" )
@Repository
public interface UserRepository extends
        PagingAndSortingRepository<Users, Long>,
        JpaRepository<Users, Long> {

    /*
    List<Users> findByName(@Param("name") String name);


    List<Users> findUsersByScore(Long score);

    List<Users> findUserByEmail(String email);

    //Users findUserById(Long id);
    Users findUserById(@Param("id") Long id);

    Users findUserByScore(Long score);


    @GetMapping("plants") public static String test() { return "test"; }*/
}
