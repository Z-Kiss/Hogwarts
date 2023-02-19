package com.codecool.hogwarts_potions.service;

import com.codecool.hogwarts_potions.model.Room;
import com.codecool.hogwarts_potions.model.Student;
import com.codecool.hogwarts_potions.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    private RoomService roomService;
    @Autowired
    public StudentService(StudentRepository studentRepository, RoomService roomService) {
        this.studentRepository = studentRepository;
        this.roomService = roomService;
    }

    public List<Student> getAllStudents(){
        Iterable<Student> studentIterator = studentRepository.findAll();
        List<Student> student = new ArrayList<>();
        studentIterator.forEach(student::add);
        return student;
    }

    public void addStudent(Student student){
        studentRepository.save(student);
    }
    public void getRoomForAllStudents(){
        List<Student> studentsWithoutRoom = studentRepository.findStudentsByRoom(null);
        studentsWithoutRoom.forEach(this::findRoomForStudent);
    }

    public void findRoomForStudent(Student student){
        Room room = roomService.findSuitableRoomForStudentByHouseType(student.getHouseType());
        room.addStudent(student);
        student.setRoom(room);
        studentRepository.save(student);
        roomService.updateRoomById(room.getId(), room);
    }

    public Student getStudentById(Long id){
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        }else {
            throw new NoSuchElementException("There is no student with ID: "+id);
        }
    }

    public void updateStudentById(Long id, Student updatedStudent){
    Student oldStudent = getStudentById(id);
    if(updatedStudent.getPetType() != null){
        oldStudent.setPetType(updatedStudent.getPetType());
    }
    if(updatedStudent.getHouseType() != null){
        oldStudent.setHouseType(updatedStudent.getHouseType());
    }
    if(updatedStudent.getRoom() != null){
        oldStudent.setRoom(updatedStudent.getRoom());
    }
    if(updatedStudent.getName() != null){
        oldStudent.setName(updatedStudent.getName());
    }
    studentRepository.save(oldStudent);
    }

    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }
}
