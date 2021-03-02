package com.nj4s.roulette.service;

import java.util.ArrayList;
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
		return turnRepository.save(turn);
	}

	public Turn findActiveTurn(Long rouletteId) {
		// FIXME filter from database 
		Iterable<Turn> turnList = turnRepository.findAll();
		List<Turn> turnLista = new ArrayList<>();
		for(Turn turn : turnList) {
			turnLista.add(turn);
		}
		return turnLista.stream().filter(x -> x.getRouletteId().equals(rouletteId) && x.getIsSpinning()).findAny()
				.orElse(null);
	}
	
}
