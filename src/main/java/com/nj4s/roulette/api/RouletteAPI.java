package com.nj4s.roulette.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nj4s.roulette.dto.Roulette;
import com.nj4s.roulette.service.FacadeService;

@RestController
public class RouletteAPI {

	@Autowired
	FacadeService facadeService;

	@GetMapping(value = "/roulette")
	public Iterable<Roulette> findAll() {
		return facadeService.findAllRoulettes();
	}

	@PostMapping(value = "/roulette")
	public Roulette create(@RequestBody Roulette roulette) {
		return facadeService.createRoulette(roulette);
	}

	@GetMapping(value = "/roulette/{rouletteId}")
	public Roulette findById(@PathVariable Long rouletteId) {
		return facadeService.findRouletteById(rouletteId);
	}

	// TODO Investigate the correct way to use PATCH according to RFC6902
	@PatchMapping(value = "/roulette/open", produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public String open(@RequestBody Long rouletteId) {
		return facadeService.openRoulette(rouletteId);
	}

	@PatchMapping(value = "/roulette/close")
	public String close(@RequestBody Long rouletteId) {
		return facadeService.closeRoulette(rouletteId);
	}

}
