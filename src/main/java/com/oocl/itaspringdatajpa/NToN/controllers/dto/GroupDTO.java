package com.oocl.itaspringdatajpa.NToN.controllers.dto;

import com.oocl.itaspringdatajpa.NToN.entities.Group;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
public class GroupDTO {
	private Long id;
	private String name;
	private List<UserDTO> userDTOList = new LinkedList<>();

	public GroupDTO(Group group) {
		this.id = group.getId();
		this.name = group.getName();
		this.userDTOList = group.getUsers().stream()
				.map(user -> new UserDTO(user))
				.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<UserDTO> getUserDTOList() {
		return userDTOList;
	}
}
