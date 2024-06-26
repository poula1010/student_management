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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;


public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
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
    public ResponseEntity<CourseDto> addCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setCourseName(courseDto.getCourseName());
        course.setDescription(courseDto.getDescription());
        course = courseRepository.save(course);
        return new ResponseEntity<>(CourseDto.toCourseDto(course),HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> deleteCourseById(int courseId) {
        try{

            Course course = courseRepository.findById(courseId).orElseThrow();

            /* alternatively we can when defining the tables in mysql/postgres (teachers_courses , students_courses) define
             a foreign key constraint on Delete cascade and on update cascade for better performance
            as we delete by course_id in one single query instead of one query per student and teacher
            and the two for loops would no longer be required;*/
            for(Student student: course.getStudents()){
                student.removeCourse(course);
            }
            for(Teacher teacher: course.getTeachers()){
                teacher.removeCourse(course);
            }
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
