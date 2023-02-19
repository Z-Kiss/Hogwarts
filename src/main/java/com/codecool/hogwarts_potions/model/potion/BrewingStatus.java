package com.codecool.hogwarts_potions.model.potion;

public enum BrewingStatus {
    BREW("Brew"),
    REPLICA("Replica"),
    DISCOVERY("Discovery");

    private String name;

    BrewingStatus(String name) {
        this.name = name;
    }

    public String toString(){
        return this.name;
    }
}
