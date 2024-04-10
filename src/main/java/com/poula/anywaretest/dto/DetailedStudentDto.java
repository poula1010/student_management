package com.poula.anywaretest.dto;

import com.poula.anywaretest.entity.Student;
import lombok.Data;

import java.util.List;

@Data
public class DetailedStudentDto {
    private int studentId;
    private String firstName;
    private String lastName;
    private List<CourseDto> courses;
    private List<QuizDto> quizzes;

    public static DetailedStudentDto toDetailedStudentDto(Student student){
        DetailedStudentDto detailedStudentDto = new DetailedStudentDto();
        detailedStudentDto.setStudentId(student.getStudentId());
        detailedStudentDto.setFirstName(student.getFirstName());
        detailedStudentDto.setLastName(student.getLastName());

        List<QuizDto> quizDtos = student.getQuizzes().stream().map(QuizDto::toQuizDto).toList();
        detailedStudentDto.setQuizzes(quizDtos);

        List<CourseDto> courseDtos = student.getCourses().stream().map(CourseDto::toCourseDto).toList();
        detailedStudentDto.setCourses(courseDtos);
        return detailedStudentDto;
    }
}
