package com.codecool.hogwarts_potions.repository;

import com.codecool.hogwarts_potions.model.potion.Ingredient;
import com.codecool.hogwarts_potions.model.potion.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

        List<Recipe> getRecipesByIngredientsContaining(Ingredient ingredient);
}
