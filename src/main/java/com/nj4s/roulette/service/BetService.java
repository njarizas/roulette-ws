package com.nj4s.roulette.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj4s.roulette.dao.BetRepository;
import com.nj4s.roulette.dto.Bet;
import com.nj4s.roulette.dto.BetOptionEnum;
import com.nj4s.roulette.dto.Turn;

@Service
public class BetService {

	@Autowired
	BetRepository betRepository;
	
	private static final Logger log = Logger.getLogger(FacadeService.class);

	public List<Bet> findAll() {
		return betRepository.findAll();
	}

	public Bet createBet(Bet bet) {
		if (!isValidStakeAmount(bet)) {
//			 throw new Exception("Bet Amount is not valid");
			log.warn("Bet Amount is not valid: " + bet);
			return null;
		}
		if (!isValidStakeOption(bet)) {
//			 throw new Exception("Bet Option is not valid");
			log.warn("Bet Option is not valid: " + bet);
			return null;
		}
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

	public List<Bet> findBetsForTurn(Turn turn) {
		// FIXME filter from database 
		List<Bet> lista = betRepository.findAll();
		return lista.stream().filter(x -> x.getTurnId().equals(turn.getTurnId()))
				.collect(Collectors.toList());
	}

}
