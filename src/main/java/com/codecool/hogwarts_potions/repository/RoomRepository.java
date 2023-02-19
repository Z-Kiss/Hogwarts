package com.codecool.hogwarts_potions.repository;

import com.codecool.hogwarts_potions.model.HouseType;
import com.codecool.hogwarts_potions.model.Room;
import com.codecool.hogwarts_potions.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {



}
