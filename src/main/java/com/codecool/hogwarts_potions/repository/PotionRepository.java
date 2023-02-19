package com.codecool.hogwarts_potions.repository;

import com.codecool.hogwarts_potions.model.potion.Potion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PotionRepository extends CrudRepository<Potion, Long> {

    List<Potion> getPotionsByStudentId(Long id);

    Potion getPotionsById(Long id);
}
