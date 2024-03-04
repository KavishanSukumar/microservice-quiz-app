package com.mylearnings.questionservice.controller;



import com.mylearnings.questionservice.model.Question;
import com.mylearnings.questionservice.model.QuestionWrapper;
import com.mylearnings.questionservice.model.Response;
import com.mylearnings.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("language/{lan}")
    public ResponseEntity<List<Question>> getQuestionsByLanguage(@PathVariable("lan") String language){
            return questionService.getQuestionsByLanguage(language);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.AddQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String language,@RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(language,numQuestions);
    }

    @PostMapping("getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> ids){
        return questionService.getQuestionsFromId(ids);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}
