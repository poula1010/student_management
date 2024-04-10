package com.poula.anywaretest.service;

import com.poula.anywaretest.dto.DetailedTeacherDto;
import com.poula.anywaretest.dto.TeacherDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {

    ResponseEntity<DetailedTeacherDto> getTeacherById(int teacherId);

    ResponseEntity<List<TeacherDto>> getAllTeachers();

    ResponseEntity<DetailedTeacherDto> addCourseToTeacher(int teacherId,int courseId);
    ResponseEntity<TeacherDto> addTeacher(TeacherDto teacherDto);

    ResponseEntity<Boolean> deleteTeacherById(int teacherId);

    ResponseEntity<TeacherDto> updateTeacher(int teacherId,TeacherDto teacherDto);
}
