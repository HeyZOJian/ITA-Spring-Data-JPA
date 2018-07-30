package com.oocl.itaspringdatajpa.OneToN.repositories;

import com.oocl.itaspringdatajpa.OneToN.controllers.dto.EmployeeDTO;
import com.oocl.itaspringdatajpa.OneToN.entities.Company;
import com.oocl.itaspringdatajpa.OneToN.entities.Employee;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	TestEntityManager entityManager;

	@Test
	public void should_return_all_employee()throws Exception {
		Employee employee1 = entityManager.persistFlushFind(new Employee("vito1","male"));
		Employee employee2 = entityManager.persistFlushFind(new Employee("vito2","male"));
		List<Employee> employees = employeeRepository.findAll();

		Assertions.assertThat( employees.get(0).getId()).isEqualTo(employee1.getId());
		Assertions.assertThat( employees.get(0).getName()).isEqualTo(employee1.getName());
		Assertions.assertThat( employees.get(1).getId()).isEqualTo(employee2.getId());
		Assertions.assertThat( employees.get(1).getName()).isEqualTo(employee2.getName());
	}

	@Test
	public void should_return_correct_employee_when_find_by_id()throws  Exception{
		Employee employee = new Employee("vito","male");
		Long id = Long.valueOf(entityManager.persistAndGetId(employee).toString());
		Employee employee1 = employeeRepository.findById(id).get();

		Assertions.assertThat( employee1.getId()).isEqualTo(employee.getId());
		Assertions.assertThat( employee1.getName()).isEqualTo(employee1.getName());
	}

	@Test
	public void should_return_correct_employee_when_find_by_gender()throws  Exception{
		Employee employee1 = entityManager.persistFlushFind(new Employee("vito1","male"));
		Employee employee2 = entityManager.persistFlushFind(new Employee("vito2","female"));
		Employee returnEmployee = employeeRepository.findByGender("male");
		Assertions.assertThat( returnEmployee.getId()).isEqualTo(employee1.getId());
		Assertions.assertThat( returnEmployee.getName()).isEqualTo(employee1.getName());
		Assertions.assertThat( returnEmployee.getGender()).isEqualTo(employee1.getGender());
	}

	@Test
	public void should_return_deleted_employee_when_given_a_exist_employee_id()throws Exception {
		Employee employee = entityManager.persistFlushFind(new Employee("vito1", "male"));
		Long id = Long.valueOf(entityManager.persistAndGetId(employee).toString());
		Employee employee1 = employeeRepository.findById(id).get();
		Assertions.assertThat( employee1.getId()).isEqualTo(employee.getId());
		Assertions.assertThat( employee1.getName()).isEqualTo(employee.getName());
		Assertions.assertThat( employee1.getGender()).isEqualTo(employee.getGender());
		employeeRepository.deleteById(id);
		Optional<Employee> employee2 = employeeRepository.findById(id);
		Assertions.assertThat( employee2.isPresent()).isFalse();

	}
}