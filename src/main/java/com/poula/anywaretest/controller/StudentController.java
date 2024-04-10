package com.poula.anywaretest.controller;

import com.poula.anywaretest.dto.DetailedStudentDto;
import com.poula.anywaretest.dto.StudentDto;
import com.poula.anywaretest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;

    @Autowired public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        return this.studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedStudentDto> getStudentById(@PathVariable("id") int studentId){
        return  this.studentService.getStudentById(studentId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStudentById(@PathVariable("id") int studentId){
        return this.studentService.deleteStudentById(studentId);
    }

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto){
        return this.studentService.addStudent(studentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") int studentId,@RequestBody StudentDto studentDto){
        return this.studentService.updateStudentById(studentId,studentDto);
    }

    @PostMapping("/{studentId}/course/{courseId}")
    public ResponseEntity<DetailedStudentDto> addCourseToStudent(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId){
        return this.studentService.addCourseToStudent(studentId,courseId);
    }
}
