package com.nj4s.roulette.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name = "turns")
public class Turn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer turnId;
	private Integer rouletteId;
	private Boolean isSpinning;
	private Integer turnResult;

	public Integer getTurnId() {
		return turnId;
	}

	public void setTurnId(Integer turnId) {
		this.turnId = turnId;
	}

	public Integer getRouletteId() {
		return rouletteId;
	}

	public void setRouletteId(Integer rouletteId) {
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
