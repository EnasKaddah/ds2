package com.example.exam_service.service;

import com.example.exam_service.dao.AnswerDao;
import com.example.exam_service.dao.QuestionDao;
import com.example.exam_service.model.Answer;
import com.example.exam_service.model.AnswerDto;
import com.example.exam_service.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private AnswerDao answerDao;
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> submitAnswer(AnswerDto dto) {
        Optional<Question> questionOpt = questionDao.findById(dto.getQuestionId());
        if (questionOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Question not found.");
        }
        Question question = questionOpt.get();
        boolean result = question.getCorrectAnswer().equalsIgnoreCase(dto.getAnswer());
        Answer answer = new Answer();
        answer.setExamId(dto.getExamId());
        answer.setQuestionId(dto.getQuestionId());
        answer.setStudentId(dto.getStudentId());
        answer.setAnswer(dto.getAnswer());
        answer.setResult(result);
        answerDao.save(answer);
        return ResponseEntity.ok(result ? "Correct answer." : "Incorrect answer.");
    }

    public ResponseEntity<List<Answer>> listAnswers(Integer studentId, Integer examId) {
        return ResponseEntity.ok(answerDao.findByStudentIdAndExamId(studentId, examId));
    }
} 