package com.codecool.hogwarts_potions.model.potion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "RECIPE_ID")
    private Long id;

    public Recipe(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Ingredient> ingredients  = new HashSet<>();

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Potion> potionList = new ArrayList<>();

    public void addPotion(Potion potion){
        potionList.add(potion);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recipe recipe = (Recipe) o;
        return id != null && Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
