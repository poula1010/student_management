package com.poula.anywaretest.service;

import com.poula.anywaretest.dto.StudentDto;
import com.poula.anywaretest.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity<StudentDto> addStudent(StudentDto studentDto);
    ResponseEntity<Boolean> deleteStudentById(int studentId);
    ResponseEntity<StudentDto> updateStudentById(int StudentId,StudentDto studentDto);

    ResponseEntity<StudentDto> getStudentById(int studentId);

    ResponseEntity<List<StudentDto>> getAllStudents();
}
