package com.poula.anywaretest.service;

import com.poula.anywaretest.dto.QuizDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {

    ResponseEntity<QuizDto> getQuizById(long quizId);
    ResponseEntity<QuizDto> addQuiz(QuizDto quizDto);

    ResponseEntity<QuizDto> updateQuiz(long quizId,QuizDto quizDto);

    ResponseEntity<Boolean> deleteQuizById(long quizId);


}
