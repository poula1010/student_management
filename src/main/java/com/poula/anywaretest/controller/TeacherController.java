package com.poula.anywaretest.controller;

import com.poula.anywaretest.dto.DetailedTeacherDto;
import com.poula.anywaretest.dto.TeacherDto;
import com.poula.anywaretest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailedTeacherDto> getTeacherById(@PathVariable("id") int teacherId){
        return this.teacherService.getTeacherById(teacherId);
    }
    @PostMapping("/{teacherId}/course/{courseId}")
    public ResponseEntity<DetailedTeacherDto> addCourseToTeacher(@PathVariable("teacherId") int teacherId,@PathVariable("courseId") int courseId){
        return this.teacherService.addCourseToTeacher(teacherId,courseId);
    }
    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers(){
        return this.teacherService.getAllTeachers();
    }
    @PostMapping
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacherDto){
        return this.teacherService.addTeacher(teacherDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTeacherById(@PathVariable("id") int teacherId){
        return this.teacherService.deleteTeacherById(teacherId);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable("id") int teacherId,@RequestBody TeacherDto teacherDto){
        return this.teacherService.updateTeacher(teacherId,teacherDto);
    }
}
