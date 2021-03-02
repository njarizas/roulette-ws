package com.nj4s.roulette.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nj4s.roulette.dto.Bet;
import com.nj4s.roulette.dto.BetOptionEnum;
import com.nj4s.roulette.dto.Roulette;
import com.nj4s.roulette.dto.RouletteStateEnum;
import com.nj4s.roulette.dto.Turn;
import com.nj4s.roulette.exceptions.BadRequestException;
import com.nj4s.roulette.util.Constants;

@Service
public class FacadeService {

	private static final Logger log = Logger.getLogger(FacadeService.class);

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
			log.info("Result: " + turn.getTurnResult() + (turn.getTurnResult() % 2 == 0 ? " Red" : " Black"));
			for (Bet bet : bets) {
				payProfit(bet, turn);
			}
			openBets(roulette);// start a new twist
		}
		return bets;
	}

	private void payProfit(Bet bet, Turn turn) {
		if (bet.getBetOption().getOptionValue().equals(turn.getTurnResult())) {
			bet.setGain(bet.getBetAmount() * Constants.GAIN_FACTOR_TO_NUMBER);
		} else if (bet.getBetOption().equals(BetOptionEnum.BLACK) && turn.getTurnResult() % 2 == 1) {
			bet.setGain(bet.getBetAmount() * Constants.GAIN_FACTOR_TO_COLOR);
		} else if (bet.getBetOption().equals(BetOptionEnum.RED) && turn.getTurnResult() % 2 == 0) {
			bet.setGain(bet.getBetAmount() * Constants.GAIN_FACTOR_TO_COLOR);
		} else {
			bet.setGain(0d);
		}
		betService.save(bet);
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

	public List<Bet> findAllBets() {
		return betService.findAll();
	}

	public Bet createBet(Bet bet) throws BadRequestException {
		Roulette roulette = rouletteService.findById(bet.getRouletteId());
		if (roulette == null) {
			log.warn("Roulette does not exist");
			throw new BadRequestException("Roulette does not exist");
		}
		if (!rouletteService.rouletteIsOpen(roulette)) {
			log.warn("Roulette is closed: " + roulette);
			 throw new BadRequestException("Roulette is closed");
		}
		Turn activeTurn = turnService.findActiveTurn(bet.getRouletteId());
		bet.setTurnId(activeTurn.getTurnId());
		return betService.createBet(bet);

	}

}
