package com.bazhenov.studentproject.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bazhenov.studentproject.Student;
import com.bazhenov.studentproject.StudentRepository;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }


    record NewStudentRequest(
            String name, String email, Integer age
    ) {

    }
    @PostMapping
    public void addStudent(@RequestBody NewStudentRequest request) {
        Student student = new Student();
        student.setAge(request.age());
        student.setEmail(request.email());
        student.setName(request.name());
        studentRepository.save(student);
    }

    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentRepository.deleteById(id);
    }
}
