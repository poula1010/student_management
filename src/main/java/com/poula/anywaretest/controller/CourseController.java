package com.poula.anywaretest.controller;

import com.poula.anywaretest.dto.CourseDto;
import com.poula.anywaretest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable("id") int courseId){
        return this.courseService.getCourseById(courseId);
    }
    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses(){
        return this.courseService.getAllCourses();
    }
    @PostMapping
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto){
        return this.courseService.addCourse(courseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCourseById(@PathVariable("id") int courseId){
        return this.courseService.deleteCourseById(courseId);
    }
    @PutMapping("/{id}")
    ResponseEntity<CourseDto> updateCourseById(@PathVariable("id") int courseId,@RequestBody CourseDto courseDto){
        return this.courseService.updateCourseById(courseId,courseDto);
    }
}
