package com.oocl.itaspringdatajpa.OneToOne.entities;

import javax.persistence.*;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
@Entity
public class Klass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "klass")
	private Leader leader;

	public Klass() {
	}

	public Klass(String name) {
		this.name = name;
	}

	public Klass(String name, Leader leader) {
		this.name = name;
		this.leader = leader;
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

	public Leader getLeader() {
		return leader;
	}

	public void setLeader(Leader leader) {
		this.leader = leader;
	}
}
