package com.poula.anywaretest.dto;

import com.poula.anywaretest.entity.Course;
import lombok.Data;

import java.util.List;

@Data
public class CourseDto {
    private int courseId;
    private String courseName;
    private String description;


    public static CourseDto toCourseDto(Course course){
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        courseDto.setDescription(course.getDescription());
        return courseDto;
    }
}
