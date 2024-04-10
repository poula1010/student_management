package com.poula.anywaretest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @Getter(AccessLevel.NONE)
    @ManyToMany(cascade =  {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(
            name="teachers_courses",
            joinColumns = @JoinColumn(name = "teacher_id",referencedColumnName = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "course_id")
    )
    private Set<Course> courses;

    public Set<Course> getCourses() {
        if(courses == null) return new HashSet<>();
        return courses;
    }

    public void addCourse(Course course){
        if(courses == null){
            courses = new HashSet<>();
        }
        courses.add(course);
    }
    public void removeCourse(Course course){
        if(courses == null) return;
        courses.remove(course);
    }
}
