package com.nj4s.roulette.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Bet")
public class Bet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	Long betId;
	Long rouletteId;
	Long turnId;
	BetOptionEnum betOption;
	Double betAmount;
	Double gain;

	public Bet() {
		super();
	}

	public Bet(Long rouletteId, BetOptionEnum betOption, Double betAmount) {
		super();
		this.rouletteId = rouletteId;
		this.betOption = betOption;
		this.betAmount = betAmount;
	}

	public Long getBetId() {
		return betId;
	}

	public void setBetId(Long betId) {
		this.betId = betId;
	}

	public Long getRouletteId() {
		return rouletteId;
	}

	public void setRouletteId(Long rouletteId) {
		this.rouletteId = rouletteId;
	}

	public Long getTurnId() {
		return turnId;
	}

	public void setTurnId(Long turnId) {
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
