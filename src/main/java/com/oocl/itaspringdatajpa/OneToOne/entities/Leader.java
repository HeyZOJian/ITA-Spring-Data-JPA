package com.oocl.itaspringdatajpa.OneToOne.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Vito Zhuang on 7/26/2018.
 */
@Entity
public class Leader {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private int age;
	@JsonIgnore
	@JoinColumn(name = "book_id")
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "leader")
	private Klass klass;

	public Leader() {
	}

	public Leader(String name, int age) {
		this.name = name;
		this.age = age;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Klass getKlass() {
		return klass;
	}

	public void setKlass(Klass klass) {
		this.klass = klass;
	}
}
