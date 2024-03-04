package com.mylearnings.quizservice.controller;


import com.mylearnings.quizservice.dto.QuizDto;
import com.mylearnings.quizservice.model.QuestionWrapper;
import com.mylearnings.quizservice.model.Response;
import com.mylearnings.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getLanguage(),quizDto.getNumQuestions(), quizDto.getTitle());
    }

    @GetMapping("get/{qid}")
    public  ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable("qid") Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.caculateResults(id,responses);

    }

}
