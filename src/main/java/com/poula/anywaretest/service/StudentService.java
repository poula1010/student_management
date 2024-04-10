package com.poula.anywaretest.service;

import com.poula.anywaretest.dto.DetailedStudentDto;
import com.poula.anywaretest.dto.StudentDto;
import com.poula.anywaretest.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity<StudentDto> addStudent(StudentDto studentDto);
    ResponseEntity<Boolean> deleteStudentById(int studentId);
    ResponseEntity<StudentDto> updateStudentById(int studentId,StudentDto studentDto);

    ResponseEntity<DetailedStudentDto> addCourseToStudent(int studentId,int courseId);

    ResponseEntity<DetailedStudentDto> getStudentById(int studentId);

    ResponseEntity<List<StudentDto>> getAllStudents();
}
