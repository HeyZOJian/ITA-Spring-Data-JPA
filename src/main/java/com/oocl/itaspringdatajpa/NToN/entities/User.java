package com.oocl.itaspringdatajpa.NToN.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany(mappedBy = "users")
	private List<Group> groups;

	public User() {
	}

	public User(String name, List<Group> groups) {
		this.name = name;
		this.groups = groups;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}
