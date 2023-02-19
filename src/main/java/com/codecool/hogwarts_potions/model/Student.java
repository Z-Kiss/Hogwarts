package com.codecool.hogwarts_potions.model;

import com.codecool.hogwarts_potions.model.potion.Potion;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "STUDENT_ID")
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private HouseType houseType;
    @Enumerated(EnumType.STRING)
    private PetType petType;


    public Student(String name, HouseType houseType, PetType petType) {
        this.name = name;
        this.houseType = houseType;
        this.petType = petType;
    }

    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    @JsonIgnore
    private Room room;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Potion> potions = new ArrayList<>();

    public void addPotion(Potion potion){
        potions.add(potion);
    }
    @Override
    public String toString() {
        return String.format("Student[id=%d, name='%s', housetype='%s', pettype='%s']",
                id, name, houseType, petType);
    }




}
