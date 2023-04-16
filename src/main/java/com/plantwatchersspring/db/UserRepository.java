package com.plantwatchersspring.db;

import com.plantwatchersspring.models.Users;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {

    List<Users> findUsersByScore(Long score);

    List<Users> findUserByEmail(String email);

    Users findUserById(Long id);

    Users findUserByScore(Long score);

}
