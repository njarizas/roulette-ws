package com.nj4s.roulette.dao;

import org.springframework.data.repository.CrudRepository;

import com.nj4s.roulette.dto.Bet;

public interface BetRepository extends CrudRepository<Bet, Integer> {

}
