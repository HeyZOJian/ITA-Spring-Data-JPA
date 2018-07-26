package com.oocl.itaspringdatajpa.oneToN.repositories;

import com.oocl.itaspringdatajpa.oneToN.controllers.dto.EmployeeDTO;
import com.oocl.itaspringdatajpa.oneToN.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}