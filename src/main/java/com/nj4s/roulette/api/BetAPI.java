package com.nj4s.roulette.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nj4s.roulette.dto.Bet;
import com.nj4s.roulette.dto.Roulette;
import com.nj4s.roulette.exceptions.BadRequestException;
import com.nj4s.roulette.service.FacadeService;

@RestController
public class BetAPI {

	@Autowired
	FacadeService facadeService;

	@GetMapping(value = "/bet")
	public List<Bet> findAll() {
		return facadeService.findAllBets();
	}

	@PostMapping(value = "/bet")
	public Bet placeBet(@RequestBody Bet bet) throws BadRequestException {
		return facadeService.createBet(bet);
	}

	@PatchMapping(value = "/bet/close")
	public List<Bet> close(@RequestBody Integer rouletteId) {
		Roulette roulette = facadeService.findRouletteById(rouletteId);
		return facadeService.closeBets(roulette);
	}

}
