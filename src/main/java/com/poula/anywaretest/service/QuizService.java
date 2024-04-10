package com.poula.anywaretest.service;

import com.poula.anywaretest.dto.QuizDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    ResponseEntity<QuizDto> getQuizById(int quizId);
    ResponseEntity<List<QuizDto>> getAllQuizzesByStudentId(int studentId);
    ResponseEntity<List<QuizDto>> getAllQuizzesByCourseId(int courseId);
    ResponseEntity<QuizDto> addQuiz(QuizDto quizDto);

    ResponseEntity<QuizDto> updateQuiz(QuizDto quizDto);

    ResponseEntity<Boolean> deleteQuizById(int quizId);


}
