package com.codecool.hogwarts_potions.model.potion;

import com.codecool.hogwarts_potions.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Potion {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "POTION_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BrewingStatus brewingStatus = BrewingStatus.BREW;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients  = new HashSet<>();

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public int sizeOfIngredient(){
        return ingredients.size();
    }

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    @JsonIgnore
    private Student student;

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    @JsonIgnore
    private Recipe recipe;

}