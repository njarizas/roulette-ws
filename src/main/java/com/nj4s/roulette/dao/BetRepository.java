package com.nj4s.roulette.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nj4s.roulette.dto.Bet;

public interface BetRepository extends JpaRepository<Bet, Integer> {

}
