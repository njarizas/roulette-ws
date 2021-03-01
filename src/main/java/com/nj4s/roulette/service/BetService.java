package com.nj4s.roulette.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj4s.roulette.dao.BetRepository;
import com.nj4s.roulette.dao.RouletteRepository;
import com.nj4s.roulette.dao.TurnRepository;
import com.nj4s.roulette.dto.Bet;
import com.nj4s.roulette.dto.BetOptionEnum;
import com.nj4s.roulette.dto.Roulette;
import com.nj4s.roulette.dto.RouletteStateEnum;
import com.nj4s.roulette.dto.Turn;

@Service
public class BetService {

	@Autowired
	BetRepository betRepository;
	@Autowired
	RouletteRepository rouletteRepository;
	@Autowired
	TurnRepository turnRepository;
	@Autowired
	TurnService turnService;

	public List<Bet> findAll() {
		return betRepository.findAll();
	}

	public Bet createBet(Bet bet) {
		if (!isValidStakeAmount(bet)) {
//			 throw new Exception("Bet Amount is not valid");
			System.out.println("Bet Amount is not valid");
			return null;
		}
		if (!isValidStakeOption(bet)) {
//			 throw new Exception("Bet Option is not valid");
			System.out.println("Bet Option is not valid");
			return null;
		}
		Roulette roulette = rouletteRepository.findById(bet.getRouletteId()).orElse(null);
		if (roulette == null) {
			return null;
		}
		if (!rouletteIsOpen(roulette)) {
			// throw new Exception("Roulette is closed");
			System.out.println("Roulette is closed");
			return null;
		}
		Turn activeTurn = turnService.findActiveTurn(roulette.getRouletteId());
		bet.setTurnId(activeTurn.getTurnId());
		return betRepository.saveAndFlush(bet);
	}
	
	public Bet save(Bet bet) {
		return betRepository.saveAndFlush(bet);
	}

	private boolean isValidStakeAmount(Bet bet) {
		return bet.getBetAmount() < 10_000 && bet.getBetAmount() >= 0.01;
	}

	private boolean isValidStakeOption(Bet bet) {
		return Arrays.asList(BetOptionEnum.values()).contains(bet.getBetOption());
	}

	private boolean rouletteIsOpen(Roulette roulette) {
		return roulette.getState().equals(RouletteStateEnum.OPEN);
	}

	public List<Bet> findBetsForTurn(Turn turn) {
		// FIXME filter from database 
		List<Bet> lista = betRepository.findAll();
		return lista.stream().filter(x -> x.getTurnId().equals(turn.getTurnId()))
				.collect(Collectors.toList());
	}

}
