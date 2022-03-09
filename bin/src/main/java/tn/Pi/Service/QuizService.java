package tn.Pi.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.RepoTrainig;
import tn.Pi.entities.Quiz;
import tn.Pi.entities.Training;


@Service
public class QuizService implements IQuizService{

	@Autowired
	QuizRepository quizr;
	@Autowired
	RepoTrainig tr;
	@Override
	public Quiz addQuiz(Quiz quiz) {
		return quizr.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return quizr.save(quiz);
	}

	@Override
	public List<Quiz> findQuizs() {
		
		return(List<Quiz>)quizr.findAll();
	}

	@Override
	public void deleteQuiz(Long idQuiz) {
		quizr.deleteById(idQuiz);
		
	}

	@Override
	public void deleteQuiz(Quiz quiz) {
		quizr.delete(quiz);
		
	}
	@Override
	public void ajouterFormationEtaffecterListeQuizs(Long idTraining, Long idQuiz) {
		Training train = tr.findById(idTraining).orElse(null);
		Quiz quiz = quizr.findById(idQuiz).orElse(null);
		quiz.setTraining(train);
		quizr.save(quiz);
	}
	

}
