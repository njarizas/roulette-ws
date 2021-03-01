package com.nj4s.roulette.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "bets")
public class Bet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer betId;
	Integer rouletteId;
	Integer turnId;
	BetOptionEnum betOption;
	Double betAmount;
	Double gain;

	public Bet() {
		super();
	}

	public Bet(Integer rouletteId, BetOptionEnum betOption, Double betAmount) {
		super();
		this.rouletteId = rouletteId;
		this.betOption = betOption;
		this.betAmount = betAmount;
	}

	public Integer getBetId() {
		return betId;
	}

	public void setBetId(Integer betId) {
		this.betId = betId;
	}

	public Integer getRouletteId() {
		return rouletteId;
	}

	public void setRouletteId(Integer rouletteId) {
		this.rouletteId = rouletteId;
	}

	public Integer getTurnId() {
		return turnId;
	}

	public void setTurnId(Integer turnId) {
		this.turnId = turnId;
	}

	public BetOptionEnum getBetOption() {
		return betOption;
	}

	public void setBetOption(BetOptionEnum betOption) {
		this.betOption = betOption;
	}

	public Double getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(Double betAmount) {
		this.betAmount = betAmount;
	}

	public Double getGain() {
		return gain;
	}

	public void setGain(Double gain) {
		this.gain = gain;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		if (betId != null) {
			builder.append("betId\":\"");
			builder.append(betId);
			builder.append("\", ");
		}
		if (rouletteId != null) {
			builder.append("rouletteId\":\"");
			builder.append(rouletteId);
			builder.append("\", ");
		}
		if (turnId != null) {
			builder.append("turnId\":\"");
			builder.append(turnId);
			builder.append("\", ");
		}
		if (betOption != null) {
			builder.append("betOption\":\"");
			builder.append(betOption);
			builder.append("\", ");
		}
		if (betAmount != null) {
			builder.append("betAmount\":\"");
			builder.append(betAmount);
			builder.append("\", ");
		}
		if (gain != null) {
			builder.append("gain\":\"");
			builder.append(gain);
		}
		builder.append("\"}");
		return builder.toString();
	}

}
