package com.nj4s.roulette.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj4s.roulette.dto.Bet;
import com.nj4s.roulette.dto.BetOptionEnum;
import com.nj4s.roulette.dto.Roulette;
import com.nj4s.roulette.dto.RouletteStateEnum;
import com.nj4s.roulette.dto.Turn;
import com.nj4s.roulette.util.Constants;

@Service
public class FacadeService {

	@Autowired
	RouletteService rouletteService;

	@Autowired
	BetService betService;

	@Autowired
	TurnService turnService;

	public Roulette createRoulette(Roulette roulette) {
		Roulette r = rouletteService.save(roulette);
		if (r.getState().equals(RouletteStateEnum.OPEN)) {
			openRoulette(r.getRouletteId());
		}
		return r;

	}

	public String openRoulette(Integer rouletteId) {
		Roulette roulette = findRouletteById(rouletteId);
		String retorno = rouletteService.openRoulette(roulette);
		openBets(roulette);
		return retorno;
	}

	public String closeRoulette(Integer rouletteId) {
		Roulette roulette = findRouletteById(rouletteId);
		closeBets(roulette);
		return rouletteService.closeRoulette(roulette);
	}

	public List<Bet> closeBets(Roulette roulette) {
		List<Bet> bets = new ArrayList<>();
		if (roulette.getState().equals(RouletteStateEnum.OPEN)) {
			// current turn ends
			Turn turn = turnService.findActiveTurn(roulette.getRouletteId());
			turn.setIsSpinning(false);
			turn.setTurnResult(rouletteService.getRouletteResult());
			turnService.save(turn);
			bets = betService.findBetsForTurn(turn);
			System.out.print("Result: " + turn.getTurnResult());
			System.out.println(turn.getTurnResult()%2==0?" Red":" Black");
			// pay profit
			payProfit(bets, turn);
			openBets(roulette);// start a new twist
		}
		return bets;
	}

	private void payProfit(List<Bet> bets, Turn turn) {
		for (Bet bet : bets) {
			if (bet.getBetOption().getOptionValue().equals(turn.getTurnResult())) {
				bet.setGain(bet.getBetAmount() * Constants.GAIN_FACTOR_TO_NUMBER);
				System.out.println("full");
			} else if (bet.getBetOption().equals(BetOptionEnum.BLACK) && turn.getTurnResult() % 2 == 1) {
				bet.setGain(bet.getBetAmount() * Constants.GAIN_FACTOR_TO_COLOR);
				System.out.println("Black");
			} else if (bet.getBetOption().equals(BetOptionEnum.RED)
					&& turn.getTurnResult() % 2 == 0) {
				bet.setGain(bet.getBetAmount() * Constants.GAIN_FACTOR_TO_COLOR);
				System.out.println("Red");
			} else {
				bet.setGain(0d);
			}
			betService.save(bet);
		}
	}

	public void openBets(Roulette roulette) {
		Turn turn = new Turn();
		turn.setRouletteId(roulette.getRouletteId());
		turn.setIsSpinning(true);
		turnService.save(turn);
	}

	public List<Roulette> findAllRoulettes() {
		return rouletteService.findAll();
	}

	public Roulette findRouletteById(Integer rouletteId) {
		return rouletteService.findById(rouletteId);
	}

}
