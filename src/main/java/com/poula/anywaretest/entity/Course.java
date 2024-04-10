package com.poula.anywaretest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "courses" , cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Student> students;

    @ManyToMany(mappedBy = "courses" ,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Teacher> teachers;
}
