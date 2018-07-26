package com.oocl.itaspringdatajpa.OneToOne.controllers.dto;

import com.oocl.itaspringdatajpa.OneToOne.entities.Klass;

import java.util.Optional;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
public class KlassDTO {
	private Long id;
	private String name;
	private LeaderDTO leaderDTO;

	public KlassDTO(Optional<Klass> byId) {
	}

	public KlassDTO(Klass klass) {
		this.id = klass.getId();
		this.name = klass.getName();
		this.leaderDTO = new LeaderDTO(klass.getLeader());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LeaderDTO getLeaderDTO() {
		return leaderDTO;
	}
}
