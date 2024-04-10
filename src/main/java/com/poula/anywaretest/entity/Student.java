package com.poula.anywaretest.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
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

    @Getter(AccessLevel.NONE)
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name="students_courses",
            joinColumns = @JoinColumn(name = "student_id",referencedColumnName = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    )
    private List<Course> courses;
    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

    public List<Course> getCourses() {
        if(courses== null){
            courses= new ArrayList<>();
        }
        return courses;
    }

    public List<Quiz> getQuizzes() {
        if (quizzes==null){
            quizzes =new ArrayList<>();
        }
        return quizzes;
    }

    public void addQuiz(Quiz quiz){
        if(quizzes == null){
            this.quizzes = new ArrayList<>();
        }
        quizzes.add(quiz);
    }

    public void removeQuiz(Quiz quiz){
        quizzes.remove(quiz);
    }

    public void addCourse(Course course){
        if(courses == null){
            this.courses = new ArrayList<>();
        }
        courses.add(course);
    }

    public void removeCourse(Course course){
        courses.remove(course);
    }
}
