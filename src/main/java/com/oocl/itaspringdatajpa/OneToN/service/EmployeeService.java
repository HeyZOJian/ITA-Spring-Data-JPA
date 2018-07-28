package com.oocl.itaspringdatajpa.OneToN.service;

import com.oocl.itaspringdatajpa.OneToN.controllers.dto.EmployeeDTO;
import com.oocl.itaspringdatajpa.OneToN.entities.Employee;
import com.oocl.itaspringdatajpa.OneToN.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vito Zhuang on 7/28/2018.
 */
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<EmployeeDTO> findAll(){
		return employeeRepository.findAll().stream()
				.map(employee -> new EmployeeDTO(employee))
				.collect(Collectors.toList());
	}

	public List<EmployeeDTO> findAllByPaging(PageRequest pageRequest){
		return employeeRepository.findAll(pageRequest).stream()
				.map(employee -> new EmployeeDTO(employee))
				.collect(Collectors.toList());
	}

	public EmployeeDTO findById(Long id){
		Employee employee = employeeRepository.findById(id).get();
		if(employee!=null){
			return new EmployeeDTO(employee);
		}
		else return null;
	}

	public EmployeeDTO findByGender(String gender){
		Employee employee = employeeRepository.findByGender(gender);
		if(employee!=null){
			return new EmployeeDTO(employee);
		}
		else return null;
	}


	public Employee addEmployee(Employee employee){
		return employeeRepository.save(employee);
	}

	public Employee updateEmployee(Long id,Employee employee){
		Employee employee1 = employeeRepository.findById(id).get();
		if(employee1!=null){
			employee1.setName(employee.getName()!=null?employee.getName():employee1.getName());
			employee1.setGender(employee.getGender()!=null?employee.getGender():employee1.getGender());
			employee1.setCompany(employee.getCompany()!=null?employee.getCompany():employee1.getCompany());
			return employeeRepository.save(employee1);
		}
		else{
			return null;
		}
	}


	public Employee deleteEmployee(Long id){
		Employee employee = employeeRepository.findById(id).get();
		if(employee != null) {
			employeeRepository.deleteById(id);
			return employee;
		}
		else{
			return null;
		}
	}

}
