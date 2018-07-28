package com.oocl.itaspringdatajpa.OneToN.repositories;

import com.oocl.itaspringdatajpa.OneToN.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	public Employee findByGender(String gender);
	int deleteEmployeeById(Long id);
}