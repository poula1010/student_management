package com.poula.anywaretest.controller;

import com.poula.anywaretest.dto.QuizDto;
import com.poula.anywaretest.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/quiz")
public class QuizController {
    private QuizService quizService;
    @Autowired
    public QuizController(QuizService quizService){
        this.quizService =quizService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuizById(@PathVariable("id") long quizId){
        return this.quizService.getQuizById(quizId);
    }
    @PostMapping
    public ResponseEntity<QuizDto> addQuiz(@RequestBody QuizDto quizDto){
        return this.quizService.addQuiz(quizDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<QuizDto> updateQuiz(@PathVariable("id") long quizId,@RequestBody QuizDto quizDto){
        return this.quizService.updateQuiz(quizId,quizDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteQuizById(@PathVariable("id") long quizId){
        return this.quizService.deleteQuizById(quizId);
    }
}
