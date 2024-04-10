package com.poula.anywaretest.service;

import com.poula.anywaretest.dto.TeacherDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeacherService {

    ResponseEntity<TeacherDto> getTeacherById(int teacherId);

    ResponseEntity<List<TeacherDto>> getAllTeachers();

    ResponseEntity<TeacherDto> addTeacher(TeacherDto teacherDto);

    ResponseEntity<Boolean> deleteTeacherById(int teacherId);

    ResponseEntity<TeacherDto> updateTeacher(int teacherId,TeacherDto teacherDto);
}
