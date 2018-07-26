package com.oocl.itaspringdatajpa.OneToOne.controllers.dto;

import com.oocl.itaspringdatajpa.OneToOne.entities.Leader;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
public class LeaderDTO {
	private Long id;
	private String name;
	private int age;
	private Long klassId;

	public LeaderDTO(Leader leader) {
		this.id = leader.getId();
		this.name = leader.getName();
		this.age = leader.getAge();
		this.klassId = leader.getKlass().getId();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Long getKlassId() {
		return klassId;
	}
}
