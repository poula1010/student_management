package com.poula.anywaretest.repository;

import com.poula.anywaretest.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
