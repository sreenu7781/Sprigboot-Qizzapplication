package com.sreenu.quizapp.controller;

import com.sreenu.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController<Question> {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestion")
    public List<Question> getAllQuestions(){
        return (List<Question>) questionService.getAllQuestions();  
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return (List<Question>) questionService.getQuestionsByCategory(category);
    }
    @PostMapping ("add")
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion((com.sreenu.quizapp.model.Question) question);
    }
}