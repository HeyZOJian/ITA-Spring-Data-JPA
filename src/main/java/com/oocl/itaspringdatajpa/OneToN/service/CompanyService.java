package com.oocl.itaspringdatajpa.OneToN.service;

import com.oocl.itaspringdatajpa.OneToN.controllers.dto.CompanyDTO;
import com.oocl.itaspringdatajpa.OneToN.controllers.dto.EmployeeDTO;
import com.oocl.itaspringdatajpa.OneToN.entities.Company;
import com.oocl.itaspringdatajpa.OneToN.entities.Employee;
import com.oocl.itaspringdatajpa.OneToN.repositories.CompanyRepository;
import com.oocl.itaspringdatajpa.OneToN.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Vito Zhuang on 7/28/2018.
 */
@Service
public class CompanyService {
	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Company> findAll(){
		return companyRepository.findAll();
	}

	public Company findById(Long id){
		return companyRepository.findAllById(id);
	}

	public List<Employee> findCompanyAllEmployees(Long id){
		Company company = companyRepository.findAllById(id);
		if(company!=null) {
			return company.getEmployees();
		}else{
			return null;
		}
	}

	public List<CompanyDTO> findAllByPaging(PageRequest pageRequest){
		return companyRepository.findAll(pageRequest).stream()
				.map(CompanyDTO::new)
				.collect(Collectors.toList());
	}

	public Company addCompany(Company company){
		company.getEmployees().stream()
				.forEach(employee -> employee.setCompany(company));
		return companyRepository.save(company);
	}

	public boolean addEmployeeIntoCompany(Long id, Employee employee){
		Company company = companyRepository.findAllById(id);
		if(company!=null) {
			employee.setCompany(company);
			employeeRepository.save(employee);
			companyRepository.save(company);
			return true;
		}
		else{
			return false;
		}
	}

	public boolean updateCompany(Long id,Company company) {
		Company company1 = companyRepository.findById(id).get();
		if (company1 != null) {
			company1.setName(company.getName() != null ? company.getName() : company1.getName());
			companyRepository.save(company1);
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteCompanyAllEmployees(Long id){
		Company company = companyRepository.findAllById(id);
		if(company != null){
			List<Employee> employees = company.getEmployees();
			company.setEmployees(null);
			for(Employee employee:employees){
				employeeRepository.delete(employee);
			}
			companyRepository.save(company);
			return true;
		}
		else{
			return false;
		}
	}

}

/*
GET       /companies    #获取company列表
GET       /companies/1  #获取某个具体company
GET       /companies/1/employees  #获取某个具体company下所有employee列表
GET       /companies?page=1&size=5  #分页查询，page等于1，size等于5
POST      /companies    #增加一个company
PUT       /companies/1  #更新某个company
DELETE    /companies/1  #删除某个company以及名下所有employees
 */
