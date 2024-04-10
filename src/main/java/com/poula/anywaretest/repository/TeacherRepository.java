package com.poula.anywaretest.repository;

import com.poula.anywaretest.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
