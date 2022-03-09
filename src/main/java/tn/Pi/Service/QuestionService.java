package tn.Pi.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.QuestionRepository;
import tn.Pi.entities.Question;
import tn.Pi.entities.Quiz;



@Service
public class QuestionService implements IQuestionService{

	@Autowired
	QuestionRepository qr;
	@Autowired
	QuizRepository quizr;
	@Override
	public Question addQuestion(Question q) {
		return qr.save(q);
	}

	@Override
	public Question updateQuestion(Question q) {
		return qr.save(q);
	}

	@Override
	public List<Question> findQuestions() {
		return (List<Question>)qr.findAll();
	}

	@Override
	public void deleteQuestion(Long idQ) {
		qr.deleteById(idQ);
		
	}

	@Override
	public void deleteQuestion(Question q) {
		qr.delete(q);
	}
	@Override
	public void affecterQuestionaQuiz(Long idQ, Long idQuiz) {
		Question question = qr.findById(idQ).orElse(null);
		Quiz quiz = quizr.findById(idQuiz).orElse(null);
		quiz.getQuestions().add(question);
		quizr.save(quiz);
	}

}
