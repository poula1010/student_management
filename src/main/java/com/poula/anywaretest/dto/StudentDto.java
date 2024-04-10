package com.poula.anywaretest.dto;

import com.poula.anywaretest.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private int studentId;
    private String firstName;
    private String lastName;

    public static StudentDto toStudentDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setStudentId(student.getStudentId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        return studentDto;
    }
}
