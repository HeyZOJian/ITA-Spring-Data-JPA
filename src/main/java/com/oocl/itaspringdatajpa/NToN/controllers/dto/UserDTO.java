package com.oocl.itaspringdatajpa.NToN.controllers.dto;

import com.oocl.itaspringdatajpa.NToN.entities.User;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
public class UserDTO {
	private Long id;
	private String name;

	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
