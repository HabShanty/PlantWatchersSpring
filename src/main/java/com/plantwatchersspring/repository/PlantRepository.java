package com.plantwatchersspring.repository;

import com.plantwatchersspring.entity.Plant;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PlantRepository extends CrudRepository<Plant, Long> {

    List<Plant> findPlantByUserId(Long id);

    List<Plant> findPlantByType(String type);

    Plant findPlantById(long id);

    Plant findPlantByName(String name);

}
