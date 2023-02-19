package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.model.potion.BrewingStatus;
import com.codecool.hogwarts_potions.model.potion.Ingredient;
import com.codecool.hogwarts_potions.model.potion.Potion;
import com.codecool.hogwarts_potions.model.potion.Recipe;
import com.codecool.hogwarts_potions.repository.IngredientRepository;
import com.codecool.hogwarts_potions.repository.PotionRepository;
import com.codecool.hogwarts_potions.repository.RecipeRepository;
import com.codecool.hogwarts_potions.service.constants.BrewingServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.*;

@Service
public class BrewService {

    private final IngredientRepository ingredientRepository;

    private final RecipeRepository recipeRepository;

    private final PotionRepository potionRepository;

    @Autowired
    public BrewService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository, PotionRepository potionRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
        this.potionRepository = potionRepository;
    }

    public void registerPotion(Potion potion){
        Set<Ingredient> ingredients = potion.getIngredients();

        ingredients.forEach(ingredient -> {
            if (ingredientRepository.countByName(ingredient.getName()) == 0){
                ingredientRepository.save(ingredient);
            }else {
                ingredient.setId(ingredientRepository.getIngredientByName(ingredient.getName()).getId());
            }
        });
        if(potion.sizeOfIngredient() == BrewingServiceConstants.MAX_INGREDIENTS_FOR_POTIONS){
            checkForRecipe(potion);
        }
        potionRepository.save(potion);
    }

    private void checkForRecipe(Potion potion){
        List<Recipe> allRecipe = (List<Recipe>) recipeRepository.findAll();

        Optional<Recipe> recipeOfPotion = allRecipe.stream()
                .filter(recipe -> recipe.getIngredients().equals(potion.getIngredients())).findFirst();

        Recipe recipeToUse;
        if(recipeOfPotion.isPresent()){
            recipeToUse = recipeOfPotion.get();
            potion.setBrewingStatus(BrewingStatus.REPLICA);
            potion.setName(potion.getStudent().getName() + " " + potion.getBrewingStatus().toString() + " #" + potion.getStudent().getId());
        }else {
            recipeToUse = new Recipe(new HashSet<>(potion.getIngredients()));
            potion.setBrewingStatus(BrewingStatus.DISCOVERY);
            potion.setName(potion.getStudent().getName() + " " + potion.getBrewingStatus().toString() + " #" + potion.getStudent().getId());
        }
        recipeToUse.addPotion(potion);
        registerRecipe(recipeToUse);
        potion.setRecipe(recipeToUse);


    }

    public void registerIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public void registerRecipe(Recipe recipe){
        recipeRepository.save(recipe);
    }

    public List<Potion> getAllPotionOfStudent(long id){
        return potionRepository.getPotionsByStudentId(id);
    }

    public List<Recipe> getRecipesByIngredient(Set<Ingredient> ingredients){
        Set<Recipe> allRecipesThatContainIngredient = new HashSet<>();
        ingredients.forEach(ingredient -> allRecipesThatContainIngredient.addAll(recipeRepository.getRecipesByIngredientsContaining(ingredient)));
        return new ArrayList<>(allRecipesThatContainIngredient);
    }


}
