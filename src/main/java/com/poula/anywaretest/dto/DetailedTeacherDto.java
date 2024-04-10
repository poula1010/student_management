package com.poula.anywaretest.dto;

import com.poula.anywaretest.entity.Course;
import com.poula.anywaretest.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class DetailedTeacherDto {
    private int id;
    private String firstName;
    private String lastName;
    private List<CourseDto> courses;

    public static DetailedTeacherDto toDetailedTeacherDto(Teacher teacher){
        DetailedTeacherDto detailedTeacherDto = new DetailedTeacherDto();
        detailedTeacherDto.setId(teacher.getId());
        detailedTeacherDto.setFirstName(teacher.getFirstName());
        detailedTeacherDto.setLastName(teacher.getLastName());
        List<CourseDto> courses = teacher.getCourses().stream().map(CourseDto::toCourseDto).toList();
        detailedTeacherDto.setCourses(courses);
        return detailedTeacherDto;
    }
}
