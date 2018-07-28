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
import java.util.Optional;
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
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if(employeeOptional.isPresent()){
			return new EmployeeDTO(employeeOptional.get());
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


	public EmployeeDTO addEmployee(Employee employee){
		return new EmployeeDTO(employeeRepository.save(employee));
	}

	public EmployeeDTO updateEmployee(Long id,Employee employee){
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if(employeeOptional.isPresent()){
			Employee employee1 = employeeOptional.get();
			employee1.setName(employee.getName()!=null?employee.getName():employee1.getName());
			employee1.setGender(employee.getGender()!=null?employee.getGender():employee1.getGender());
			employee1.setCompany(employee.getCompany()!=null?employee.getCompany():employee1.getCompany());
			return new EmployeeDTO(employeeRepository.save(employee1));
		}
		else{
			return null;
		}
	}


	public EmployeeDTO deleteEmployee(Long id){
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if(employeeOptional.isPresent()) {
			employeeRepository.deleteById(id);
			return new EmployeeDTO(employeeOptional.get());
		}
		else{
			return null;
		}
	}

}
