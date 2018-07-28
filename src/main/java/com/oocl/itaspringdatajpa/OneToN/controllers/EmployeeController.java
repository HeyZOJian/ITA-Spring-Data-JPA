package com.oocl.itaspringdatajpa.OneToN.controllers;

import com.oocl.itaspringdatajpa.OneToN.controllers.dto.EmployeeDTO;
import com.oocl.itaspringdatajpa.OneToN.entities.Employee;
import com.oocl.itaspringdatajpa.OneToN.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vito Zhuang on 7/28/2018.
 */
@RequestMapping("/api/v1/employees")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping(path = "")
	public ResponseEntity findAll(){
		return ResponseEntity.ok(employeeService.findAll());
	}

	@GetMapping(path = "/page/{page}/pageSize/{size}")
	public ResponseEntity findAllByPaging(@PathVariable int page,@PathVariable int size){
		return ResponseEntity.ok(employeeService.findAllByPaging(new PageRequest(page-1,size)));
	}

	@GetMapping(path = "/{searchKeyWord}")
	public ResponseEntity findEmployeeBySearchKeyWord(@PathVariable String searchKeyWord){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(searchKeyWord);
		if( !isNum.matches() ) {
			EmployeeDTO employeeDTO = employeeService.findByGender(searchKeyWord);
			return employeeDTO==null?ResponseEntity.notFound().build():ResponseEntity.ok(employeeDTO);
		}else{
			EmployeeDTO employeeDTO = employeeService.findById(Long.parseLong(searchKeyWord));
			return employeeDTO==null?ResponseEntity.notFound().build():ResponseEntity.ok(employeeDTO);
		}
	}

	@PostMapping(path = "")
	public ResponseEntity addEmployee(@RequestBody Employee employee){
		return ResponseEntity.ok(employeeService.addEmployee(employee));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		EmployeeDTO employeeDTO = employeeService.updateEmployee(id,employee);
		return employeeDTO==null?ResponseEntity.notFound().build():ResponseEntity.ok(employeeDTO);
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity deleteEmployee(@PathVariable Long id){
		EmployeeDTO employeeDTO = employeeService.deleteEmployee(id);
		return employeeDTO==null?ResponseEntity.notFound().build():ResponseEntity.ok(employeeDTO);
	}
}
