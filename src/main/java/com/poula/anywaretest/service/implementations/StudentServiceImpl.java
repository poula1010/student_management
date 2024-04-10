package com.poula.anywaretest.service.implementations;

import com.poula.anywaretest.dto.StudentDto;
import com.poula.anywaretest.entity.Student;
import com.poula.anywaretest.exception.APIException;
import com.poula.anywaretest.repository.StudentRepository;
import com.poula.anywaretest.service.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
    @Override
    public ResponseEntity<StudentDto> addStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        Student addedStudent = studentRepository.save(student);
        StudentDto addedStudentDto = StudentDto.toStudentDto(addedStudent);
        return new ResponseEntity<>(addedStudentDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteStudentById(int studentId) {
        try{
            Student student = studentRepository.findById(studentId).orElseThrow();
            studentRepository.delete(student);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"this student doesn't exist");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<StudentDto> updateStudentById(int studentId,StudentDto studentDto) {
        try{
            Student student = studentRepository.findById(studentId).orElseThrow();
            student.setFirstName(studentDto.getFirstName());
            student.setLastName(studentDto.getLastName());
            Student updatedStudent = studentRepository.save(student);
            StudentDto updatedStudentDto = StudentDto.toStudentDto(updatedStudent);
            return new ResponseEntity<>(updatedStudentDto,HttpStatus.OK);
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"this student doesn't exist");
        }
    }

    @Override
    public ResponseEntity<StudentDto> getStudentById(int studentId) {
        try{
            Student student = studentRepository.findById(studentId).orElseThrow();
            StudentDto studentDto = StudentDto.toStudentDto(student);
            return new ResponseEntity<>(studentDto,HttpStatus.OK);
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"this student doesn't exist");
        }
    }

    @Override
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream().map(StudentDto::toStudentDto).collect(Collectors.toList());
        return new ResponseEntity<>(studentDtos,HttpStatus.OK);
    }
}
