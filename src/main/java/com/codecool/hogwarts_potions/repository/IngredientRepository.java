package com.codecool.hogwarts_potions.repository;

import com.codecool.hogwarts_potions.model.potion.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    int countByName(String name);
    Ingredient getIngredientByName(String name);
}
