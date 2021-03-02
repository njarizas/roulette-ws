package com.nj4s.roulette.api;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.nj4s.roulette.dto.Bet;
import com.nj4s.roulette.dto.Roulette;
import com.nj4s.roulette.exceptions.BadRequestException;
import com.nj4s.roulette.service.FacadeService;

@RestController
public class BetAPI {

	private static final Logger log = Logger.getLogger(BetAPI.class);

	@Autowired
	FacadeService facadeService;

	@GetMapping(value = "/bet")
	public Iterable<Bet> findAll() {
		return facadeService.findAllBets();
	}

	@PostMapping(value = "/bet")
	public Bet placeBet(@RequestHeader("user-id") Long userId, @RequestBody Bet bet) throws BadRequestException {
		log.info("the user " + userId + " is placing a bet: " + bet);
		return facadeService.createBet(bet);
	}

	@PatchMapping(value = "/bet/close")
	public List<Bet> close(@RequestBody Long rouletteId) {
		Roulette roulette = facadeService.findRouletteById(rouletteId);
		log.info("close bets for roulette: " + roulette);
		return facadeService.closeBets(roulette);
	}

}
