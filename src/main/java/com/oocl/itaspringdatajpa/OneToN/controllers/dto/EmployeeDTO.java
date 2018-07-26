package com.oocl.itaspringdatajpa.OneToN.controllers.dto;

import com.oocl.itaspringdatajpa.OneToN.entities.Employee;

public class EmployeeDTO{
	private final Long id;
	private final String name;
	private final Long companyId;

	public EmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.companyId = employee.getCompany().getId();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getCompanyId() {
		return companyId;
	}

}