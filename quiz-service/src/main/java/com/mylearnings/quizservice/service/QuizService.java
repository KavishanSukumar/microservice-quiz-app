package com.mylearnings.quizservice.service;


import com.mylearnings.quizservice.dao.QuizDao;
import com.mylearnings.quizservice.feign.QuizInterface;
import com.mylearnings.quizservice.model.QuestionWrapper;
import com.mylearnings.quizservice.model.Quiz;
import com.mylearnings.quizservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String language, Integer noOfQuestions, String title) {

       List<Integer> questions=quizInterface.getQuestionsForQuiz(language,noOfQuestions).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionids(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
       Quiz quiz= quizDao.findById(id).get();
        List<Integer> questionids=quiz.getQuestionids();

       ResponseEntity<List<QuestionWrapper>> questionForUser= quizInterface.getQuestionFromId(questionids);
       
       return questionForUser;
    }

    public ResponseEntity<Integer> caculateResults(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score=quizInterface.getScore(responses);
        return score;
    }
}
