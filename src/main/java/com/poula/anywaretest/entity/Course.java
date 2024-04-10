package com.poula.anywaretest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Getter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "courses" , cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Student> students;

    @Getter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "courses" ,cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Teacher> teachers;

    @Getter(AccessLevel.NONE)
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Quiz> quizzes;

    public List<Quiz> getQuizzes() {
        if(quizzes==null) {
            quizzes = new ArrayList<>();
        }
        return quizzes;
    }

    public List<Student> getStudents() {
        if(students == null) {
            students= new ArrayList<>();
        }
        return students;
    }

    public List<Teacher> getTeachers() {
        if(teachers == null){
            teachers = new ArrayList<>();
        }
        return teachers;
    }

    public void addQuiz(Quiz quiz){
        if(quizzes == null){
            quizzes = new ArrayList<>();
        }
        quizzes.add(quiz);
    }

    public void addStudent(Student student){
        if(students == null){
            students = new ArrayList<>();
        }
        students.add(student);
    }
    public void addTeacher(Teacher teacher){
        if(teachers == null){
            teachers = new ArrayList<>();
        }
        teachers.add(teacher);
    }
    public void removeStudent(Student student){
        students.remove(student);
    }
    public void removeQuiz(Quiz quiz){
        quizzes.remove(quiz);
    }
}
