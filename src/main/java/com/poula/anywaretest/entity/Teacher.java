package com.poula.anywaretest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int id;

    @Column(name = "first_name")
    private  String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(
            name="teachers_courses",
            joinColumns = @JoinColumn(name = "teacher_id",referencedColumnName = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    )
    private List<Course> courses;
}
