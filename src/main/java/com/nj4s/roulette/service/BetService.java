package com.nj4s.roulette.service;

import java.util.ArrayList;
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
import com.nj4s.roulette.exceptions.BadRequestException;

@Service
public class BetService {

	@Autowired
	BetRepository betRepository;
	
	private static final Logger log = Logger.getLogger(BetService.class);

	public Iterable<Bet> findAll() {
		return betRepository.findAll();
	}

	public Bet createBet(Bet bet) throws BadRequestException {
		if (!isValidStakeAmount(bet)) {
			log.warn("Bet Amount is not valid: " + bet);
			throw new BadRequestException("Bet Amount is not valid");
		}
		if (!isValidStakeOption(bet)) {
			log.warn("Bet Option is not valid: " + bet);
			 throw new BadRequestException("Bet Option is not valid");
			
		}
		return betRepository.save(bet);
	}
	
	public Bet save(Bet bet) {
		return betRepository.save(bet);
	}

	private boolean isValidStakeAmount(Bet bet) {
		return bet.getBetAmount() < 10_000 && bet.getBetAmount() >= 1;
	}

	private boolean isValidStakeOption(Bet bet) {
		return Arrays.asList(BetOptionEnum.values()).contains(bet.getBetOption());
	}

	public List<Bet> findBetsForTurn(Turn turn) {
		// FIXME filter from database 
		Iterable<Bet> lista = betRepository.findAll();
		List<Bet> list = new ArrayList<>();
		for (Bet bet : lista) {
			list.add(bet);
		}
		return list.stream().filter(x -> x.getTurnId().equals(turn.getTurnId()))
				.collect(Collectors.toList());
	}

}
