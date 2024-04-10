package com.poula.anywaretest.service;

import com.poula.anywaretest.dto.CourseDto;
import com.poula.anywaretest.entity.Course;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {
    ResponseEntity<CourseDto> getCourseById(int courseId);

    ResponseEntity<List<CourseDto>> getAllCourses();
    ResponseEntity<CourseDto> addCourse(CourseDto courseDto);

    ResponseEntity<Boolean> deleteCourseById(int courseId);

    ResponseEntity<CourseDto> updateCourseById(int courseId,CourseDto courseDto);
}
