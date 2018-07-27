package com.oocl.itaspringdatajpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.oocl.itaspringdatajpa.OneToN.controllers.CompanyController;
import com.oocl.itaspringdatajpa.OneToN.entities.Company;
import com.oocl.itaspringdatajpa.OneToN.repositories.CompanyRepository;
import com.oocl.itaspringdatajpa.OneToN.repositories.EmployeeRepository;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Vito Zhuang on 7/27/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CompanyController.class)
public class CompanyControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CompanyRepository companyRepository;
	@MockBean
	private EmployeeRepository employeeRepository;

//
//	@Test
//	public void addCompanies()throws Exception{
//		Company company = new Company();
//		company.setName("oocl");
//
//		given(companyRepository.save(any(Company.class))).willReturn(company);
//		mockMvc.perform(post("/api/v1/companies")
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
//		.content(json.toString()))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.content.id",))
//		.andDo(print());
//	}


	@Test
	public void findAllCompanies()throws Exception{
		List<Company> companies = new LinkedList<>();
		given(companyRepository.findAll()).willReturn(companies);

		mockMvc.perform(get("/api/v1/companies")).andExpect(status().isOk());
	}


}
