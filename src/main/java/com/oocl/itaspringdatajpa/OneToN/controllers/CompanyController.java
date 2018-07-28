package com.oocl.itaspringdatajpa.OneToN.controllers;

import com.oocl.itaspringdatajpa.OneToN.controllers.dto.CompanyDTO;
import com.oocl.itaspringdatajpa.OneToN.entities.Company;
import com.oocl.itaspringdatajpa.OneToN.entities.Employee;
import com.oocl.itaspringdatajpa.OneToN.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController{
	@Autowired
	CompanyService companyService;

	@Transactional
	@GetMapping(path = "")
	public ResponseEntity findAllCompanies(){
		return ResponseEntity.ok(companyService.findAll().stream()
				.map(CompanyDTO::new)
				.collect(Collectors.toList()));
	}

	@Transactional
	@GetMapping(path = "/{id}")
	public ResponseEntity findCompanyById(@PathVariable Long id){
		Company company = companyService.findById(id);
		if(company!=null){
			return ResponseEntity.ok(new CompanyDTO(company));
		}
		else{
			return new ResponseEntity<>("NOT_FOUND",HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path = "/page/{page}/pageSize/{size}")
	public ResponseEntity findAllByPaging(@PathVariable int page,@PathVariable int size){
		return ResponseEntity.ok(companyService.findAllByPaging(new PageRequest(page-1,size)));
	}

	@Transactional
	@PostMapping(path = "")
	public ResponseEntity<CompanyDTO> addCompany(@RequestBody Company company){
		return ResponseEntity.ok(new CompanyDTO(companyService.addCompany(company)));
	}

	@Transactional
	@PutMapping(path = "/{id}/employees")
	public ResponseEntity addEmployeeIntoCompany(@PathVariable Long id, @RequestBody Employee employee) {
		if (companyService.addEmployeeIntoCompany(id, employee)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else{
			return new ResponseEntity<>("NOT_FOUND",HttpStatus.NOT_FOUND);
		}
	}

	@Transactional
	@PutMapping(path = "/{id}")
	public ResponseEntity updateCompany(@PathVariable Long id, @RequestBody Company company) {
		if (companyService.updateCompany(id, company)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		else{
			return new ResponseEntity<>("NOT_FOUND",HttpStatus.NOT_FOUND);
		}
	}


	@Transactional
	@DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity deleteCompany(@PathVariable Long id){
		if(companyService.deleteCompanyAllEmployees(id)){
			return ResponseEntity.status(HttpStatus.OK).build();
		}else{
			return new ResponseEntity<>("NOT_FOUND",HttpStatus.NOT_FOUND);
		}
	}
}