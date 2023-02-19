package com.codecool.hogwarts_potions.model;

public enum PetType {
    CAT("cat"),
    RAT("rat"),
    OWL("owl"),
    NONE("none");

    private String name;

    PetType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
