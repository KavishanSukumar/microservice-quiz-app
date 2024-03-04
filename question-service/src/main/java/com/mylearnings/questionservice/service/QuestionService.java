package com.mylearnings.questionservice.service;

import com.mylearnings.questionservice.dao.QuestionDao;
import com.mylearnings.questionservice.model.Question;
import com.mylearnings.questionservice.model.QuestionWrapper;
import com.mylearnings.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByLanguage(String language) {
        try {
            return  new ResponseEntity<>(questionDao.findByLanguage(language),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> AddQuestion(Question question) {
        questionDao.save(question);
        return new ResponseEntity<>("success",HttpStatus.CREATED) ;
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String language, Integer numQuestions) {
        List<Integer> questions=questionDao.findRandomQuestionsByLanguage(language,numQuestions);

        return new ResponseEntity<>(questions,HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> ids) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();

        for (Integer i:ids){
            questions.add(questionDao.findById(i).get());
        }

        for (Question question:questions){
            QuestionWrapper questionWrapper=new QuestionWrapper();
            questionWrapper.setId(question.getId());
            questionWrapper.setQuestionText(question.getQuestionText());
            questionWrapper.setOption1(question.getOption1());
            questionWrapper.setOption2(question.getOption2());
            questionWrapper.setOption3(question.getOption3());
            questionWrapper.setOption4(question.getOption4());
            wrappers.add(questionWrapper);
        }

        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {

        int right=0;
        for (Response response:responses){
            Question question=questionDao.findById(response.getId()).get();
            if (response.getResponse().equals(question.getCorrectOption())) {
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
