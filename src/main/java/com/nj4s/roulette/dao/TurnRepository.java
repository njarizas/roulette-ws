package com.nj4s.roulette.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nj4s.roulette.dto.Turn;

public interface TurnRepository extends JpaRepository<Turn, Integer> {

}
