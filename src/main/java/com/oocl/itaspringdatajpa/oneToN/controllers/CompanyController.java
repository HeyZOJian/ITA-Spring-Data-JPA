package com.oocl.itaspringdatajpa.oneToN.controllers;

import com.oocl.itaspringdatajpa.oneToN.controllers.dto.CompanyDTO;
import com.oocl.itaspringdatajpa.oneToN.entities.Company;
import com.oocl.itaspringdatajpa.oneToN.entities.Employee;
import com.oocl.itaspringdatajpa.oneToN.repositories.CompanyRepository;
import com.oocl.itaspringdatajpa.oneToN.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController{
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	@Transactional
	@GetMapping(path = "")
	public List<Company> findAllCompanies(){
		return companyRepository.findAll();
	}

	@Transactional
	@GetMapping(path = "/{id}")
	public Company findCompanyById(@PathVariable Long id){
		return companyRepository.findAllById(id);
	}

	@Transactional
	@PostMapping(path = "")
	public Company addCompany(@RequestBody Company company){
		company.getEmployees().stream()
				.forEach(employee -> employee.setCompany(company));
		return companyRepository.save(company);
	}

	@Transactional
	@PutMapping(path = "/{id}")
	public ResponseEntity updateCompany(@PathVariable Long id, @RequestBody Employee employee){
		Company company = companyRepository.findAllById(id);
		employee.setCompany(company);
		employeeRepository.save(employee);
		companyRepository.save(company);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Transactional
	@DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Company deleteCompany(@PathVariable Long id){
		Company company = companyRepository.findById(id).get();
		companyRepository.delete(company);
		return company;

	}
}