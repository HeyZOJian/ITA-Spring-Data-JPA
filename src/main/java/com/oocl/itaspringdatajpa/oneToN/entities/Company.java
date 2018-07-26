package com.oocl.itaspringdatajpa.oneToN.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Company{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@CreatedDate
	private ZonedDateTime createDate = ZonedDateTime.now();

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "company", fetch = FetchType.LAZY)
	private List<Employee>employees = new LinkedList<>();

	public Company() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ZonedDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(ZonedDateTime createDate) {
		this.createDate = createDate;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}