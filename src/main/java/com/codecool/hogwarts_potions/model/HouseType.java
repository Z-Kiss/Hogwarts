package com.codecool.hogwarts_potions.model;

public enum HouseType {
    GRYFFINDOR("Griffindor"),
    HUFFLEPUFF("Hufflepuff"),
    RAVENCLAW("Rawenclaw"),
    SLYTHERIN("Slytherin");

    private String  name;

    HouseType(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
