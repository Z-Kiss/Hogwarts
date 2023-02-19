package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.model.potion.Recipe;
import com.codecool.hogwarts_potions.model.request.BrewRequest;
import com.codecool.hogwarts_potions.model.request.PotionCreateRequest;
import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.model.potion.Ingredient;
import com.codecool.hogwarts_potions.model.potion.Potion;
import com.codecool.hogwarts_potions.repository.PotionRepository;
import com.codecool.hogwarts_potions.service.BrewService;
import com.codecool.hogwarts_potions.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/potion")
public class BrewController {
    private BrewService brewService;

    private StudentService studentService;
    private final PotionRepository potionRepository;

    @Autowired
    public BrewController(BrewService brewService, StudentService studentService,
                          PotionRepository potionRepository) {
        this.brewService = brewService;
        this.studentService = studentService;
        this.potionRepository = potionRepository;
    }

    @PostMapping
    public void brewPotion(@RequestBody PotionCreateRequest potionCreateRequest){
        Student student = studentService.getStudentById(potionCreateRequest.getStudentId());
        Potion potion = potionCreateRequest.getPotion();
        potion.setStudent(student);
        potion.setName(student.getName());
        student.addPotion(potion);
        brewService.registerPotion(potion);

    }

    @GetMapping("/{student-id}")
    public List<Potion> getAllPotionOfStudent(@PathVariable("student-id") long id){
        return brewService.getAllPotionOfStudent(id);
    }
    @PostMapping("/addingredient")
    public void registerIngredient(@RequestBody Ingredient ingredient){
        brewService.registerIngredient(ingredient);
    }

    @PostMapping("/brew")
    public Potion startBrewingPotion(@RequestBody BrewRequest brewRequest){
        Student student = studentService.getStudentById(brewRequest.getStudentId());
        Potion potion = new Potion();
        student.addPotion(potion);
        potion.setStudent(student);
        potionRepository.save(potion);
        studentService.updateStudentById(student.getId(), student);
        System.out.println(potion.getStudent());
        return potion;
    }

    @PutMapping("/{potion-id}/add")
    public Potion addIngredientToPotion(@PathVariable("potion-id") Long id, @RequestBody Ingredient ingredient){
        Potion potion = potionRepository.getPotionsById(id);
        potion.addIngredient(ingredient);
        brewService.registerPotion(potion);
        return potion;
    }

    @GetMapping("/{potion-id}/help")
    public List<Recipe> getRecipeForPotion(@PathVariable("potion-id") Long id){
        Potion potion = potionRepository.getPotionsById(id);
        return brewService.getRecipesByIngredient(potion.getIngredients());

    }
}
