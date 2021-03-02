package com.nj4s.roulette.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Turn")
public class Turn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long turnId;
	private Long rouletteId;
	private Boolean isSpinning;
	private Integer turnResult;

	public Long getTurnId() {
		return turnId;
	}

	public void setTurnId(Long turnId) {
		this.turnId = turnId;
	}

	public Long getRouletteId() {
		return rouletteId;
	}

	public void setRouletteId(Long rouletteId) {
		this.rouletteId = rouletteId;
	}

	public Boolean getIsSpinning() {
		return isSpinning;
	}

	public void setIsSpinning(Boolean isSpinning) {
		this.isSpinning = isSpinning;
	}

	public Integer getTurnResult() {
		return turnResult;
	}

	public void setTurnResult(Integer turnResult) {
		this.turnResult = turnResult;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		if (turnId != null) {
			builder.append("turnId\":\"");
			builder.append(turnId);
			builder.append("\", ");
		}
		if (rouletteId != null) {
			builder.append("rouletteId\":\"");
			builder.append(rouletteId);
			builder.append("\", ");
		}
		if (isSpinning != null) {
			builder.append("isSpinning\":\"");
			builder.append(isSpinning);
			builder.append("\", ");
		}
		if (turnResult != null) {
			builder.append("turnResult\":\"");
			builder.append(turnResult);
		}
		builder.append("\"}");
		return builder.toString();
	}
	
	

}
