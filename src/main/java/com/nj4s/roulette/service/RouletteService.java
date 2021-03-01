package com.nj4s.roulette.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj4s.roulette.dao.RouletteRepository;
import com.nj4s.roulette.dto.Roulette;
import com.nj4s.roulette.dto.RouletteStateEnum;

@Service
public class RouletteService {

	private Random r = new Random();

	@Autowired
	RouletteRepository rouletteRepository;

	public Roulette save(Roulette roulette) {
		return rouletteRepository.saveAndFlush(roulette);
	}

	public List<Roulette> findAll() {
		return rouletteRepository.findAll();
	}

	public Roulette findById(Integer rouletteId) {
		return rouletteRepository.findById(rouletteId).orElse(null);
	}

	public String openRoulette(Roulette roulette) {
		try {
			if (roulette.getState().equals(RouletteStateEnum.OPEN)) {
				return "The roulette " + roulette.getRouletteId() + " already is open";
			} else {
				roulette.setState(RouletteStateEnum.OPEN);
				save(roulette);
				return "Operation Success";
			}
		} catch (Exception e) {
			return "Operation Failed";
		}
	}

	public String closeRoulette(Roulette roulette) {
		try {
			if (roulette.getState().equals(RouletteStateEnum.CLOSED)) {
				return "The roulette " + roulette.getRouletteId() + " already is closed";
			} else {
				roulette.setState(RouletteStateEnum.CLOSED);
				save(roulette);
				return "Operation Success";
			}
		} catch (Exception e) {
			return "Operation Failed";
		}
	}

	public Integer getRouletteResult() {
		return r.nextInt(37);
	}

}
