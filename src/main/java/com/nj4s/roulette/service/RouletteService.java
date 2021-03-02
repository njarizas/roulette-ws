package com.nj4s.roulette.service;

import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj4s.roulette.dao.RouletteRepository;
import com.nj4s.roulette.dto.Roulette;
import com.nj4s.roulette.dto.RouletteStateEnum;
import com.nj4s.roulette.exceptions.BadRequestException;

@Service
public class RouletteService {

	private Random r = new Random();

	private static final Logger log = Logger.getLogger(RouletteService.class);

	@Autowired
	RouletteRepository rouletteRepository;

	public Roulette save(Roulette roulette) {
		rouletteRepository.save(roulette);
		return roulette;
	}

	public Iterable<Roulette> findAll() {
		return rouletteRepository.findAll();
	}

	public Roulette findById(Long rouletteId) {
		return rouletteRepository.findById(rouletteId).orElse(null);
	}

	public String openRoulette(Roulette roulette) {
		if (roulette == null) {
			log.warn("Roulette does not exist");
			throw new BadRequestException("The roulette does not exist");
		}
		if (roulette.getState().equals(RouletteStateEnum.OPEN)) {
			log.warn("The roulette " + roulette.getRouletteId() + " already is open");
			throw new BadRequestException("The roulette " + roulette.getRouletteId() + " already is open");
		} else {
			roulette.setState(RouletteStateEnum.OPEN);
			save(roulette);
			return "Operation Success";
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

	public boolean rouletteIsOpen(Roulette roulette) {
		return roulette.getState().equals(RouletteStateEnum.OPEN);
	}

}
