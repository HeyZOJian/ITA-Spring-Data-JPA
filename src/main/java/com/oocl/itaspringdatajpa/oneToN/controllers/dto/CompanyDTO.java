package com.oocl.itaspringdatajpa.oneToN.controllers.dto;
import com.oocl.itaspringdatajpa.oneToN.controllers.dto.EmployeeDTO;
import com.oocl.itaspringdatajpa.oneToN.entities.Company;
import com.oocl.itaspringdatajpa.oneToN.entities.Employee;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyDTO{
	private final int id;
	private final String name;
	private final ZonedDateTime createDate;
	private final List<EmployeeDTO> employees;

	public CompanyDTO(Company company) {
		this.id = company.getId();
		this.name = company.getName();
		this.createDate = company.getCreateDate();
		this.employees = company.getEmployees().stream()
				.map(employee -> new EmployeeDTO(employee))
				.collect(Collectors.toList());

	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ZonedDateTime getCreateDate() {
		return createDate;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

}