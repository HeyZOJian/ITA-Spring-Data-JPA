package com.oocl.itaspringdatajpa.oneToN.controllers.dto;

import com.oocl.itaspringdatajpa.oneToN.entities.Employee;
import org.springframework.context.annotation.Bean;

public class EmployeeDTO{
	private int id;
	private String name;
	private int companyId;

	public EmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.companyId = employee.getCompany().getId();
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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
}