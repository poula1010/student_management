package com.poula.anywaretest.repository;

import com.poula.anywaretest.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
