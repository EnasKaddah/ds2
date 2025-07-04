package com.example.exam_service.service;

import com.example.exam_service.dao.ExamDao;
import com.example.exam_service.dao.QuestionDao;
import com.example.exam_service.model.Exam;
import com.example.exam_service.model.Question;
import com.example.exam_service.model.QuestionDto;
import com.example.exam_service.model.QuestionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private ExamDao examDao;

    public ResponseEntity<String> addQuestion(QuestionDto dto) {
        Optional<Exam> examOpt = examDao.findById(dto.getExamId());
        if (examOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Exam not found.");
        }
        Question q = new Question();
        q.setExam(examOpt.get());
        q.setText(dto.getText());
        q.setOptions(dto.getOptions());
        q.setCorrectAnswer(dto.getCorrectAnswer());
        questionDao.save(q);
        return ResponseEntity.ok("Question added.");
    }

    public ResponseEntity<List<QuestionWrapper>> listByExam(Integer examId) {
        List<Question> questions= questionDao.findByExamId(examId);

        List<QuestionWrapper> wrappers = questions.stream()
                .map(c -> new QuestionWrapper(c.getId(), c.getExam(), c.getText(), c.getOptions()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(wrappers);
    }
} 