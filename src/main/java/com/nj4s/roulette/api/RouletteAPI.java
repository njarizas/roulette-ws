package com.nj4s.roulette.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nj4s.roulette.dto.Roulette;
import com.nj4s.roulette.service.FacadeService;
import com.nj4s.roulette.service.RouletteService;
import com.nj4s.roulette.service.TurnService;
import com.sun.istack.NotNull;

@RestController
public class RouletteAPI {
	
	@Autowired
	FacadeService facadeService;

	@Autowired
	RouletteService rouletteService;
	
	@Autowired
	TurnService turnService;

	@GetMapping(value = "/roulette")
	public List<Roulette> findAll() {
		return rouletteService.findAll();
	}

	@PostMapping(value = "/roulette")
	public Roulette create(@RequestBody Roulette roulette) {
		return facadeService.createRoulette(roulette);
	}

	@GetMapping(value = "/roulette/{rouletteId}")
	public Roulette findById(@PathVariable @NotNull Integer rouletteId) {
		return rouletteService.findById(rouletteId);
	}

	//TODO Investigate the correct way to use PATCH according to RFC6902 
	@PatchMapping(value = "/roulette/open")
	public String open(@RequestBody Integer rouletteId) {
		return facadeService.openRoulette(rouletteId);
	}
	
	@PatchMapping(value = "/roulette/close")
	public String close(@RequestBody Integer rouletteId) {
		return facadeService.closeRoulette(rouletteId);
	}

}
