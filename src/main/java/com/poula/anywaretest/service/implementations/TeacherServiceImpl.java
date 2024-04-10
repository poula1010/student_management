package com.poula.anywaretest.service.implementations;

import com.poula.anywaretest.dto.TeacherDto;
import com.poula.anywaretest.entity.Teacher;
import com.poula.anywaretest.exception.APIException;
import com.poula.anywaretest.repository.TeacherRepository;
import com.poula.anywaretest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherServiceImpl implements TeacherService {
    private TeacherRepository teacherRepository;
    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    @Override
    public ResponseEntity<TeacherDto> getTeacherById(int teacherId) {
        try{
            Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
            TeacherDto teacherDto = TeacherDto.toTeacherDto(teacher);
            return new ResponseEntity<>(teacherDto,HttpStatus.OK);
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
