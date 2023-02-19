package com.codecool.hogwarts_potions.controller;

import com.codecool.hogwarts_potions.model.Room;
import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/accommodate-all")
    public void accommodateAllStudents(){
        studentService.getRoomForAllStudents();
    }

    @PostMapping
    public void addStudent(@RequestBody Student student){
        studentService.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student getRoomById(@PathVariable("id") Long id){
        return studentService.getStudentById(id);

    }

    @PutMapping("/{id}")
    public void updateStudentById(@PathVariable("id") Long id, @RequestBody Student updatedStudent){
        studentService.updateStudentById(id, updatedStudent);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable("id") Long id){
        studentService.deleteStudentById(id);
    }

    //Get rooms where no cat or owl lives

}
