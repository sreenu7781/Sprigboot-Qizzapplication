package com.sreenu.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sreenu.quizapp.dao.QuestionDao;
import com.sreenu.quizapp.dao.QuizDao;
import com.sreenu.quizapp.model.Quiz;
import com.sreenu.quizapp.model.QuestionWrapper;
import com.sreenu.quizapp.model.Response;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public String createQuiz(String category , int numQ,String title){
        List<com.sreenu.quizapp.model.Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return "quiz created";
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
      Optional<Quiz> quiz = quizDao.findById(id);
      List<QuestionWrapper> questionsForUsers = new ArrayList<>();
      List<Question> questionsFromDb =quiz.get().getQuestions();
      for(Question q : questionsFromDb){
          QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
          questionsForUsers.add(qw);
      }
      return questionsForUsers;
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        Quiz quiz =quizDao.findById(id).get();
        List<com.sreenu.quizapp.model.Question> questions =quiz.getQuestions();
        int i=0;
        int right = 0;
        for(Response response :responses){
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) {

                right++;
            }
                i++;
        }
        return right;
    }

    public Integer calculateResult(Integer id, java.util.List<Response> responses) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateResult'");
    }
}
