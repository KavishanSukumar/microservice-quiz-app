package com.mylearnings.questionservice.dao;

import com.mylearnings.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    List<Question> findByLanguage(String language);

    @Query(value = "SELECT q.id FROM question q WHERE q.language=:language ORDER BY RANDOM() LIMIT :noOfQuestions" ,nativeQuery = true)
    List<Integer> findRandomQuestionsByLanguage(String language, Integer noOfQuestions);
}
