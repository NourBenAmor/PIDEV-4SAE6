package com.skander.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skander.forum.model.Post;
import com.skander.forum.model.Rate;
import com.skander.forum.repos.RateRepository;


@Service
public class RateServiceImp implements RateService{

	@Autowired
	RateRepository rateRepository;
	
	@Override
	public Rate saveRate(Rate r) {
		return rateRepository.save(r);
	}

	@Override
	public Rate updateRate(Rate r) {
		return rateRepository.save(r);
	}

	@Override
	public void deleteRate(Rate r) {
		rateRepository.delete(r);
		
	}

	@Override
	public void deleteRateById(Long id) {
		rateRepository.deleteById(id);
		
	}

	@Override
	public Rate getRate(Long id) {
		return rateRepository.findById(id).get();
	}

	@Override
	public List<Rate> getAllRates() {
		
		return rateRepository.findAll();
	}
	
	@Override
	public float Statistiques(Post p)
	{
		List<Rate> rates=rateRepository.findByPost(p);
		float s = 0;
		for (int i = 0; i < rates.size(); i++){
			s=s+rates.get(i).getStars();	
		}
		return s/(rates.size());
	}
}
