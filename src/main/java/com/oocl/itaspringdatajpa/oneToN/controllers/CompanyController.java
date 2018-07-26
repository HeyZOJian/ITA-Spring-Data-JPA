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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController{
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;
	@Transactional
	@GetMapping(path = "")
	public List<CompanyDTO> findAllCompanies(){
		return companyRepository.findAll().stream()
				.map(company -> new CompanyDTO(company))
				.collect(Collectors.toList());
	}

	@Transactional
	@GetMapping(path = "/{id}")
	public CompanyDTO findCompanyById(@PathVariable Long id){
		Company company = companyRepository.findById(id).get();
		return new CompanyDTO(company);
	}

	@Transactional
	@PostMapping(path = "")
	public CompanyDTO addCompany(@RequestBody Company company){
		company.getEmployees().stream()
				.forEach(employee -> employee.setCompany(company));
		return new CompanyDTO(companyRepository.save(company));
	}

	@Transactional
	@PutMapping(path = "/{id}/employees")
	public ResponseEntity addEmployeeIntoCompany(@PathVariable Long id, @RequestBody Employee employee){
		Company company = companyRepository.findAllById(id);
		employee.setCompany(company);
		employeeRepository.save(employee);
		companyRepository.save(company);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@Transactional
	@PutMapping(path = "/{id}")
	public ResponseEntity updateCompany(@PathVariable Long id, @RequestBody Company company){
		Company company1 = companyRepository.findById(id).get();
		company1.setName(company.getName()!=null?company.getName():company1.getName());
		companyRepository.save(company1);
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