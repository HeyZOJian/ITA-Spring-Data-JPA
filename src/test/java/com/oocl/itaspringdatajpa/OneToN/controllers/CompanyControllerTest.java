package com.oocl.itaspringdatajpa.OneToN.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.itaspringdatajpa.OneToN.controllers.dto.CompanyDTO;
import com.oocl.itaspringdatajpa.OneToN.entities.Company;
import com.oocl.itaspringdatajpa.OneToN.service.CompanyService;
import com.oocl.itaspringdatajpa.OneToN.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Vito Zhuang on 7/28/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
@EnableSpringDataWebSupport
public class CompanyControllerTest {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private MockMvc mvc;

	@MockBean
	CompanyService companyService;

	@MockBean
	EmployeeService employeeService;

	@Test
	public void should_return_all_company_without_any_paramters() throws Exception {
		Company company1 = new Company("oocl");
		Company company2 = new Company("tw");
		List<Company> list = Arrays.asList(company1,company2);
		when(companyService.findAll()).thenReturn(list);

		ResultActions result = mvc.perform(get("/api/v1/companies"));

		result.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name",is("oocl")))
				.andExpect(jsonPath("$[0].name",is("tw")))
				.andDo(print());
	}

	@Test
	public void should_return_correct_company_when_given_exist_company_id() throws Exception {
		Company company = new Company("oocl");
		company.setId(1L);
		when(companyService.findById(anyLong())).thenReturn(company);

		ResultActions result = mvc.perform(get("/api/v1/companies/1"));

		result.andExpect(status().isOk())
				.andExpect(jsonPath("$.name",is("oocl")));
	}

	@Test
	public void should_return_all_company_with_page_and_page_size() throws Exception{
		Company company1 = new Company("oocl");
		Company company2 = new Company("tw");
		Company company3 = new Company("mi");
		Company company4 = new Company("wangyi");
		List<CompanyDTO> list = Arrays.asList(new CompanyDTO(company1),
				new CompanyDTO(company2),
				new CompanyDTO(company3),
				new CompanyDTO(company4));
		when(companyService.findAllByPaging(any(PageRequest.class))).thenReturn(list);

		ResultActions result = mvc.perform(get("/api/v1/companies/page/1/pageSize/4"));

		result.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[0].name",is("oocl")))
				.andExpect(jsonPath("$[1].name",is("tw")))
				.andExpect(jsonPath("$[2].name",is("mi")))
				.andExpect(jsonPath("$[3].name",is("wangyi")))
				.andDo(print());
	}

	@Test
	public void addCompany() {
	}

	@Test
	public void addEmployeeIntoCompany() {
	}

	@Test
	public void updateCompany() {
	}

	@Test
	public void deleteCompany() {
	}
}