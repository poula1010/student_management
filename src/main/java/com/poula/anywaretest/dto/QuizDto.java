package com.poula.anywaretest.dto;

import com.poula.anywaretest.entity.Quiz;
import lombok.Data;

@Data
public class QuizDto {
    private long quizId;
    private double marks;
    private double maxMarks;
    private int studentId;
    private int courseId;
    public static QuizDto toQuizDto(Quiz quiz){
        QuizDto quizDto = new QuizDto();
        quizDto.setQuizId(quiz.getId());
        quizDto.setMaxMarks(quiz.getMaxMarks());
        quizDto.setMarks(quiz.getMarks());
        quizDto.setStudentId(quiz.getStudent().getStudentId());
        quizDto.setCourseId(quiz.getCourse().getCourseId());

        return quizDto;
    }
}
