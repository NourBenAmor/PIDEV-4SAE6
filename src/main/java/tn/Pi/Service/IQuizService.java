package tn.Pi.Service;

import java.util.List;

import tn.Pi.entities.Quiz;



public interface IQuizService {
	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public List<Quiz> findQuizs();
	public void deleteQuiz(Long idQuiz);
	void deleteQuiz(Quiz quiz);
	public void ajouterFormationEtaffecterListeQuizs(Long idTraining, Long idQuiz);
}
