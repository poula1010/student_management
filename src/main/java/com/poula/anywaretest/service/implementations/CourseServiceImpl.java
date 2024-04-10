package com.poula.anywaretest.service.implementations;

import com.poula.anywaretest.dto.CourseDto;
import com.poula.anywaretest.entity.Course;
import com.poula.anywaretest.entity.Student;
import com.poula.anywaretest.entity.Teacher;
import com.poula.anywaretest.exception.APIException;
import com.poula.anywaretest.repository.CourseRepository;
import com.poula.anywaretest.repository.StudentRepository;
import com.poula.anywaretest.repository.TeacherRepository;
import com.poula.anywaretest.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository,StudentRepository studentRepository, TeacherRepository teacherRepository)
    {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;

    }
    @Override
    public ResponseEntity<CourseDto> getCourseById(int courseId) {
        try{
            Course course = courseRepository.findById(courseId).orElseThrow();
            CourseDto courseDto = CourseDto.toCourseDto(course);
            return new ResponseEntity<>(courseDto,HttpStatus.OK);
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"course couldn't be found");
        }
    }

    @Override
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtos = courses.stream().map(CourseDto::toCourseDto).collect(Collectors.toList());
        return new ResponseEntity<>(courseDtos,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CourseDto>> getAllCoursesForStudentById(int studentId) {
        try{
            Student student = studentRepository.findById(studentId).orElseThrow();
            List<Course> courses = student.getCourses();
            List<CourseDto> courseDtos = courses.stream().map(CourseDto::toCourseDto).collect(Collectors.toList());
            return new ResponseEntity<>(courseDtos,HttpStatus.OK);
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"student doesn't exist");
        }
    }

    @Override
    public ResponseEntity<List<CourseDto>> getAllCoursesForTeacherById(int teacherId) {
        try{
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        List<Course> courses = teacher.getCourses();
        List<CourseDto> courseDtos = courses.stream().map(CourseDto::toCourseDto).collect(Collectors.toList());
        return new ResponseEntity<>(courseDtos,HttpStatus.OK);}
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"teacher doesn't exist");
        }
    }

    @Override
    public ResponseEntity<CourseDto> addCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course = courseRepository.save(course);
        return new ResponseEntity<>(CourseDto.toCourseDto(course),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> deleteCourseById(int courseId) {
        try{
            Course course = courseRepository.findById(courseId).orElseThrow();
            courseRepository.delete(course);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"course couldn't be found");
        }
    }

    @Override
    public ResponseEntity<CourseDto> updateCourseById(int courseId,CourseDto courseDto) {
        try{
            Course course = courseRepository.findById(courseId).orElseThrow();
            course.setDescription(courseDto.getDescription());
            course.setCourseName(courseDto.getCourseName());
            Course updatedCourse = courseRepository.save(course);
            return new ResponseEntity<>(CourseDto.toCourseDto(updatedCourse), HttpStatus.OK);
        }catch(Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"course couldn't be found");
        }
    }
}
