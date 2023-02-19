package com.codecool.hogwarts_potions.repository;

import com.codecool.hogwarts_potions.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    List<Student> findStudentsByRoom(Object o);
}
