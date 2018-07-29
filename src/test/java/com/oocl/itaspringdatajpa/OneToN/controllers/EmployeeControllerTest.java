package com.oocl.itaspringdatajpa.OneToN.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.itaspringdatajpa.OneToN.controllers.dto.EmployeeDTO;
import com.oocl.itaspringdatajpa.OneToN.entities.Employee;
import com.oocl.itaspringdatajpa.OneToN.service.CompanyService;
import com.oocl.itaspringdatajpa.OneToN.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@EnableSpringDataWebSupport
public class EmployeeControllerTest {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mvc;

    @MockBean
    CompanyService companyService;


    @MockBean
    EmployeeService employeeService;

    @Test
    public void should_return_all_employee_info() throws Exception {
        Employee employee1 = new Employee("vito");
        Employee employee2 = new Employee("zojian");
        List<EmployeeDTO> list = Arrays.asList(new EmployeeDTO(employee1), new EmployeeDTO(employee2));
        when(employeeService.findAll()).thenReturn(list);

        ResultActions result = mvc.perform(get("/api/v1/employees"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("vito")))
                .andExpect(jsonPath("$[1].name", is("zojian")))
                .andDo(print());
    }

    @Test
    public void should_return_all_employee_with_page_and_page_size() throws Exception {
        Employee employee1 = new Employee("vito1");
        Employee employee2 = new Employee("vito2");
        Employee employee3 = new Employee("vito3");
        Employee employee4 = new Employee("vito4");

        List<EmployeeDTO> list = Arrays.asList(new EmployeeDTO(employee1),
                new EmployeeDTO(employee2),
                new EmployeeDTO(employee3),
                new EmployeeDTO(employee4));
        when(employeeService.findAllByPaging(any(PageRequest.class))).thenReturn(list);

        ResultActions result = mvc.perform(get("/api/v1/employees/page/1/pageSize/4"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].name", is("vito1")))
                .andExpect(jsonPath("$[1].name", is("vito2")))
                .andExpect(jsonPath("$[2].name", is("vito3")))
                .andExpect(jsonPath("$[3].name", is("vito4")))
                .andDo(print());
    }


    @Test
    public void should_return_correct_employee_when_given_exist_employee_id() throws Exception {
        Employee employee = new Employee("vito");
        when(employeeService.findById(anyLong())).thenReturn(new EmployeeDTO(employee));

        ResultActions result = mvc.perform(get("/api/v1/employees/1"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("vito")));
    }

    @Test
    public void should_return_not_found_when_given_not_exist_employee_id() throws Exception {
        when(employeeService.findById(anyLong())).thenReturn(null);

        ResultActions result = mvc.perform(get("/api/v1/employees/1"));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void should_return_new_employee_info_when_add_a_employee() throws Exception {
        Employee employee = new Employee("vito");
        when(employeeService.addEmployee(any(Employee.class))).thenReturn(new EmployeeDTO(employee));

        ResultActions result = mvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee)));

        result.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name", is("vito")))
                .andDo(print());
    }

    @Test
    public void should_return_status_not_content_when_update_a_exist_employee_info() throws Exception {
        Employee employee = new Employee("vito-1");
        when(employeeService.updateEmployee(anyLong(), any(Employee.class))).thenReturn(new EmployeeDTO(employee));

        ResultActions result = mvc.perform(put("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee)));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("vito-1")));
    }

    @Test
    public void should_return_status_not_found_when_update_a_not_exist_employee_info() throws Exception {
        when(employeeService.updateEmployee(anyLong(), any(Employee.class))).thenReturn(null);

        ResultActions result = mvc.perform(put("/api/v1/employees/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new Employee("vito-1"))));

        result.andExpect(status().isNotFound());
    }


    @Test
    public void should_return_status_ok_when_delete_a_exist_employee() throws Exception {
        Employee employee = new Employee("vito");
        employee.setId(1L);
        when(employeeService.deleteEmployee(anyLong())).thenReturn(new EmployeeDTO(employee));

        ResultActions result = mvc.perform(delete("/api/v1/employees/1"));

        result.andExpect(status().isOk());
    }

    @Test
    public void should_return_status_not_found_when_delete_a_not_exist_employee() throws Exception {
        when(employeeService.deleteEmployee(anyLong())).thenReturn(null);

        ResultActions result = mvc.perform(delete("/api/v1/employees/1"));

        result.andExpect(status().isNotFound());
    }
}