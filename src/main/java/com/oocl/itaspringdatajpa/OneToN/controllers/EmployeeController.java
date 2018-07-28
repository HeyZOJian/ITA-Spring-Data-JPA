package com.oocl.itaspringdatajpa.OneToN.controllers;

import com.oocl.itaspringdatajpa.OneToN.entities.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vito Zhuang on 7/28/2018.
 */
@RequestMapping("/employees")
@RestController
public class EmployeeController {

	@GetMapping(path = "")
	public ResponseEntity findAll(){
		return null;
	}

	@GetMapping(path = "/{searchKeyWord}")
	public ResponseEntity findEmployeeBySearchKeyWord(@PathVariable String searchKeyWord){
		return null;
	}

	@PostMapping(path = "")
	public ResponseEntity addEmployee(@RequestBody Employee employee){
		return null;
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		return null;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity deleteEmployee(@PathVariable Long id){
		return null;
	}
}
