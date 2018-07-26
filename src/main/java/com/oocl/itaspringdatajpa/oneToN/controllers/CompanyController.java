package com.oocl.itaspringdatajpa.oneToN.controllers;

import com.oocl.itaspringdatajpa.oneToN.controllers.dto.CompanyDTO;
import com.oocl.itaspringdatajpa.oneToN.entities.Company;
import com.oocl.itaspringdatajpa.oneToN.repositories.CompanyRepository;
import com.oocl.itaspringdatajpa.oneToN.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController{
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping(path = "")
	public List<Company> findAllCompanies(){
		return companyRepository.findAll();
	}
}