package com.poula.anywaretest.dto;

import com.poula.anywaretest.entity.Teacher;
import lombok.Data;

@Data
public class TeacherDto {
    private int teacherId;
    private String firstName;
    private String lastName;

    public static TeacherDto toTeacherDto(Teacher teacher){
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setTeacherId(teacher.getId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        return teacherDto;
    }
}
