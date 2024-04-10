package com.poula.anywaretest.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(
            name="students_courses",
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    )
    private List<Course> courses;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

//    @ManyToMany
//    @JoinTable(
//            joinColumns = @JoinColumn(name = "student_id"),
//            inverseJoinColumns = @JoinColumn(name = "quiz_id")
//    )
//    private List<Quiz> quizes;
}
