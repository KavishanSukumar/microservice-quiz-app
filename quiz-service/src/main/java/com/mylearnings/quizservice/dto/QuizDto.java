package com.mylearnings.quizservice.dto;

import lombok.Data;

@Data
public class QuizDto {
    String language;
    Integer numQuestions;
    String title;
}
