package tn.Pi.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Pi.Repository.QuestionRepository;
import tn.Pi.Repository.ResponseRepository;
import tn.Pi.entities.Question;
import tn.Pi.entities.Response;


@Service
public class ResponseService implements IResponseService{

	@Autowired
	ResponseRepository rr;
	@Autowired
	QuestionRepository qr;
	@Override
	public Response addResponse(Response Response) {
		
		return rr.save(Response);
	}

	@Override
	public Response updateResponse(Response Response) {
		return rr.save(Response);
	}

	@Override
	public List<Response> findResponses() {
		return (List<Response>)rr.findAll();
	}

	@Override
	public void deleteResponse(Long idR) {
		rr.deleteById(idR);
		
	}

	@Override
	public void deleteResponse(Response Response) {
		rr.delete(Response);
		
	}

	@Override
	public void affecterResponseaQuiz(Long idR, Long idQuiz) {
		Response response = rr.findById(idR).orElse(null);
		Question question = qr.findById(idQuiz).orElse(null);
		question.getResponses().add(response);
		qr.save(question);
		
	}

}
