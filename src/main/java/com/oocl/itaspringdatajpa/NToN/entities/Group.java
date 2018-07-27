package com.oocl.itaspringdatajpa.NToN.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
@Table(name = "oocl_group")
@Entity
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "oocl_group_user",
	joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"))
	private List<User> users = new LinkedList<>();

	public Group() {
	}

	public Group(String name, List<User> users) {
		this.name = name;
		this.users = users;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
