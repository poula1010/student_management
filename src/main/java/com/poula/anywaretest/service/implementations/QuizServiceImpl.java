package com.poula.anywaretest.service.implementations;

import com.poula.anywaretest.dto.QuizDto;
import com.poula.anywaretest.entity.Course;
import com.poula.anywaretest.entity.Quiz;
import com.poula.anywaretest.entity.Student;
import com.poula.anywaretest.exception.APIException;
import com.poula.anywaretest.repository.CourseRepository;
import com.poula.anywaretest.repository.QuizRepository;
import com.poula.anywaretest.repository.StudentRepository;
import com.poula.anywaretest.service.QuizService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


public class QuizServiceImpl implements QuizService {
    private QuizRepository quizRepository;
    private StudentRepository studentRepository;

    private CourseRepository courseRepository;

    public QuizServiceImpl(QuizRepository quizRepository, StudentRepository studentRepository, CourseRepository courseRepository){
        this.quizRepository = quizRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    @Override
    public ResponseEntity<QuizDto> getQuizById(long quizId) {
        try{
            Quiz quiz = quizRepository.findById(quizId).orElseThrow();
            return  new ResponseEntity<>(QuizDto.toQuizDto(quiz),HttpStatus.OK);
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"this quiz doesn't exist");
        }
    }

    @Transactional
    @Override
    public ResponseEntity<QuizDto> addQuiz(QuizDto quizDto) {
        Quiz quiz = new Quiz();
        quiz.setMaxMarks(quizDto.getMaxMarks());
        quiz.setMarks(quizDto.getMarks());
        Student student = studentRepository.findById(quizDto.getStudentId()).orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST,"Student not found - "));
        Course course = courseRepository.findById(quizDto.getCourseId()).orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST,"Course not found - "));

        quiz.setStudent(student);
        student.addQuiz(quiz);

        quiz.setCourse(course);
        course.addQuiz(quiz);

        Quiz addedQuiz = quizRepository.save(quiz);
        return new ResponseEntity<>(QuizDto.toQuizDto(addedQuiz),HttpStatus.CREATED);
    }
    @Transactional
    @Override
    public ResponseEntity<QuizDto> updateQuiz(long quizId,QuizDto quizDto) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST,"Quiz not found - " ));
        quiz.setMarks(quizDto.getMarks());
        quiz.setMaxMarks(quizDto.getMaxMarks());
        if(quiz.getCourse().getCourseId() != quizDto.getCourseId()){
            Course oldCourse = quiz.getCourse();
            oldCourse.removeQuiz(quiz);
            Course newCourse = courseRepository.findById(quizDto.getCourseId()).orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST,"Course not found - "));
            newCourse.addQuiz(quiz);
            quiz.setCourse(newCourse);
        }
        if(quiz.getStudent().getStudentId() != quizDto.getStudentId()){
            Student oldStudent = quiz.getStudent();
            oldStudent.removeQuiz(quiz);
            Student newStudent=  studentRepository.findById(quizDto.getStudentId()).orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST,"Student not found - "));
            newStudent.addQuiz(quiz);
            quiz.setStudent(newStudent);
        }
        Quiz updatedQuiz = quizRepository.save(quiz);
        return new ResponseEntity<>(QuizDto.toQuizDto(updatedQuiz),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> deleteQuizById(long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST,"Quiz not found - " ));
        quiz.getStudent().removeQuiz(quiz);
        quizRepository.delete(quiz);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
