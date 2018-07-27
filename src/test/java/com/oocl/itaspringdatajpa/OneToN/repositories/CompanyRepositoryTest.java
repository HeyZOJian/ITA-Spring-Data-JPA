package com.oocl.itaspringdatajpa.OneToN.repositories;

import com.oocl.itaspringdatajpa.OneToN.entities.Company;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CompanyRepositoryTest {

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void should_return_all_companies()throws Exception {
		Company saveCompany1 = entityManager.persistFlushFind(new Company("oocl-1"));
		Company saveCompany2 = entityManager.persistFlushFind(new Company("oocl-2"));
		List<Company> companies = companyRepository.findAll();

		Assertions.assertThat( companies.get(0).getId()).isEqualTo(saveCompany1.getId());
		Assertions.assertThat( companies.get(0).getName()).isEqualTo(saveCompany1.getName());
		Assertions.assertThat( companies.get(1).getId()).isEqualTo(saveCompany2.getId());
		Assertions.assertThat( companies.get(1).getName()).isEqualTo(saveCompany2.getName());
	}

	@Test
	public void should_return_correct_company_when_find_by_id()throws  Exception{
		Company saveCompany = entityManager.persistFlushFind(new Company("oocl"));
		Company company = companyRepository.findAllById(1L);

		Assertions.assertThat( company.getId()).isEqualTo(saveCompany.getId());
		Assertions.assertThat( company.getName()).isEqualTo(saveCompany.getName());
	}


}