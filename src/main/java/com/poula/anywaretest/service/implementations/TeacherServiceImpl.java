package com.poula.anywaretest.service.implementations;

import com.poula.anywaretest.dto.DetailedTeacherDto;
import com.poula.anywaretest.dto.TeacherDto;
import com.poula.anywaretest.entity.Course;
import com.poula.anywaretest.entity.Teacher;
import com.poula.anywaretest.exception.APIException;
import com.poula.anywaretest.repository.CourseRepository;
import com.poula.anywaretest.repository.TeacherRepository;
import com.poula.anywaretest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;
    private CourseRepository courseRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository,CourseRepository courseRepository){
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }
    @Override
    public ResponseEntity<DetailedTeacherDto> getTeacherById(int teacherId) {
        try{
            Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
            DetailedTeacherDto detailedTeacherDto = DetailedTeacherDto.toDetailedTeacherDto(teacher);
            return new ResponseEntity<>(detailedTeacherDto,HttpStatus.OK);
        }
        catch (Exception e ){
            throw new APIException(HttpStatus.BAD_REQUEST,"this teacher doesn't exist");
        }
    }

    @Override
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teacherDtos = teachers.stream().map(TeacherDto::toTeacherDto).collect(Collectors.toList());
        return new ResponseEntity<>(teacherDtos,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DetailedTeacherDto> addCourseToTeacher(int teacherId, int courseId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(()-> new APIException(HttpStatus.BAD_REQUEST,"this teacher doesn't exist"));
        Course course  = courseRepository.findById(courseId).orElseThrow(()-> new APIException(HttpStatus.BAD_REQUEST,"this course doesn't exist"));
        teacher.addCourse(course);
        course.addTeacher(teacher);

        Teacher updatedTeacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(DetailedTeacherDto.toDetailedTeacherDto(teacher),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TeacherDto> addTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        Teacher addedTeacher = teacherRepository.save(teacher);
        return new ResponseEntity<>(TeacherDto.toTeacherDto(addedTeacher),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> deleteTeacherById(int teacherId) {
        try {
            Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
            teacherRepository.delete(teacher);
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"this teacher doesn't exist");
        }
    }

    @Override
    public ResponseEntity<TeacherDto> updateTeacher(int teacherId,TeacherDto teacherDto) {
        try{
            Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
            teacher.setFirstName(teacherDto.getFirstName());
            teacher.setLastName(teacherDto.getLastName());
            Teacher updatedTeacher = teacherRepository.save(teacher);
            return new ResponseEntity<>(TeacherDto.toTeacherDto(updatedTeacher),HttpStatus.OK);
        }
        catch (Exception e){
            throw new APIException(HttpStatus.BAD_REQUEST,"this teacher doesn't exist");
        }
    }
}
