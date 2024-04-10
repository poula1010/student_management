package com.poula.anywaretest.service.implementations;

import com.poula.anywaretest.dto.QuizDto;
import com.poula.anywaretest.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuizServiceImpl implements QuizService {
    @Override
    public ResponseEntity<QuizDto> getQuizById(int quizId) {
        return null;
    }

    @Override
    public ResponseEntity<List<QuizDto>> getAllQuizzesByStudentId(int studentId) {
        return null;
    }

    @Override
    public ResponseEntity<List<QuizDto>> getAllQuizzesByCourseId(int courseId) {
        return null;
    }

    @Override
    public ResponseEntity<QuizDto> addQuiz(QuizDto quizDto) {
        return null;
    }

    @Override
    public ResponseEntity<QuizDto> updateQuiz(QuizDto quizDto) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> deleteQuizById(int quizId) {
        return null;
    }
}
