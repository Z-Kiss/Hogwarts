package com.codecool.hogwarts_potions.model;


import lombok.*;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ROOM_ID")
    private Long id;



    public Room(HouseType houseType) {
        this.houseType = houseType;
    }

    @Enumerated(EnumType.STRING)
    private HouseType houseType;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();


    public void addStudent(Student student){
        students.add(student);
    }

    public String toString(){
        String stringOfRoom = String.format("RoomId: %d, roomType: %s \n", id, houseType);
        return stringOfRoom;
    }


}
