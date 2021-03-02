package com.nj4s.roulette.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Roulette")
public class Roulette implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	Long rouletteId;
	RouletteStateEnum state;

	public Roulette() {
		super();
		this.state = RouletteStateEnum.CLOSED;
	}

	public Roulette(Long rouletteId, RouletteStateEnum state) {
		super();
		this.rouletteId = rouletteId;
		this.state = state;
	}

	public Long getRouletteId() {
		return rouletteId;
	}

	public void setRouletteId(Long rouletteId) {
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
