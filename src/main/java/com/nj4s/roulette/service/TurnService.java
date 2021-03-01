package com.nj4s.roulette.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj4s.roulette.dao.TurnRepository;
import com.nj4s.roulette.dto.Turn;

@Service
public class TurnService {

	@Autowired
	TurnRepository turnRepository;

	public Turn save(Turn turn) {
		return turnRepository.saveAndFlush(turn);
	}

	public Turn findActiveTurn(Integer rouletteId) {
		// FIXME: filtrar desde la base de datos
		List<Turn> turnList = turnRepository.findAll();
		return turnList.stream().filter(x -> x.getRouletteId().equals(rouletteId) && x.getIsSpinning()).findAny()
				.orElse(null);
	}

}
