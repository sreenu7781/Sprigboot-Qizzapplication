package com.sreenu.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sreenu.quizapp.model.Question;

import org.springframework.data.jpa.repository.Query;


@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
        @Query(value="SELECT * FROM question WHERE category = :category ORDER BY DBMS_RANDOM.value fetch first :numQ rows only",nativeQuery = true)
         List<Question> findRandomQuestionsByCategory(String category, int numQ) ;

        List<Question> findByCategory(String category);

}