package com.nj4s.roulette.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "roulettes")
public class Roulette implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer rouletteId;
	RouletteStateEnum state;

	public Roulette() {
		super();
		this.state = RouletteStateEnum.CLOSED;
	}

	public Roulette(Integer rouletteId, RouletteStateEnum state) {
		super();
		this.rouletteId = rouletteId;
		this.state = state;
	}

	public Integer getRouletteId() {
		return rouletteId;
	}

	public void setRouletteId(Integer rouletteId) {
		this.rouletteId = rouletteId;
	}

	public RouletteStateEnum getState() {
		return state;
	}

	public void setState(RouletteStateEnum state) {
		this.state = state;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		if (rouletteId != null) {
			builder.append("rouletteId\":\"");
			builder.append(rouletteId);
			builder.append("\", ");
		}
		if (state != null) {
			builder.append("state\":\"");
			builder.append(state);
		}
		builder.append("\"}");
		return builder.toString();
	}

}
