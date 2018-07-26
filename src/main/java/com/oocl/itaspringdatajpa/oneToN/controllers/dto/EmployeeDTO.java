package com.oocl.itaspringdatajpa.oneToN.controllers.dto;

import com.oocl.itaspringdatajpa.oneToN.entities.Employee;
import org.springframework.context.annotation.Bean;

public class EmployeeDTO{
	private final int id;
	private final String name;
	private final int companyId;

	public EmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.companyId = employee.getCompany().getId();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCompanyId() {
		return companyId;
	}

}