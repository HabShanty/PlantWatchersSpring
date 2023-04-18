package com.plantwatchersspring.repository;

import com.plantwatchersspring.entity.Plant;
import java.util.List;

import com.plantwatchersspring.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends
        PagingAndSortingRepository<Plant, Long>,
        JpaRepository<Plant, Long> {
    /*
    List<Plant> findPlantByUserId(Long id);

    List<Plant> findPlantByType(String type);

    Plant findPlantById(long id);

    Plant findPlantByName(String name);
*/
}
