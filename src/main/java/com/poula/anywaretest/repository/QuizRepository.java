package com.poula.anywaretest.repository;

import com.poula.anywaretest.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
