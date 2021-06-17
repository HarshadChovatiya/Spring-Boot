package com.sample.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sample.app.model.CustomJsonResponse;
import com.sample.app.model.Employee;
import com.sample.app.repository.impl.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;
	
	@Mock
	EmployeeRepository employeeRepository;
	
	static Employee employee;
	
	@BeforeAll
	public static void CreateEmployeeObject() {
		employee = new Employee(1,"New John", 21, 3000, "CL", 1);
	}
	
	@Test
	public void testGetEmployeeById() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(employeeRepository.getEmployeeById(Mockito.anyInt())).thenReturn(employee);		

		ResponseEntity<Employee> responseEntity = employeeController.getEmployeeById(1);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getName()).isEqualTo("New John");
	
	}

	@Test
	public void testGetAllEmployee() throws Exception {
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee(2, "David", 22, 1000, "NY", 2));
		employees.add(new Employee(3, "Mike", 21, 2000, "CL", 3));
	
		when(employeeRepository.getAllEmployee()).thenReturn(employees);		

		ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployee();
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().size()).isEqualTo(2);
	}
	
	@Test
	public void testAddEmployee() throws Exception {
		
		when(employeeRepository.addEmployee(Mockito.any())).thenReturn("Employee added");
		
		ResponseEntity<CustomJsonResponse> responseEntity = employeeController.addEmployee(employee);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getBody().getMessage()).isEqualTo("Employee added");
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		
		when(employeeRepository.updateEmployee(Mockito.anyInt(), Mockito.any())).thenReturn(employee);
		
		ResponseEntity<Employee> responseEntity = employeeController.updateEmployee(1, employee);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getEmpId()).isEqualTo(employee.getEmpId());
		
	}
	
	@Test
	public void testDeleteEmployeeById() throws Exception {
		
		when(employeeRepository.deleteEmployee(Mockito.anyInt())).thenReturn("Employee deleted");
		
		ResponseEntity<CustomJsonResponse> responseEntity = employeeController.deleteEmployee(1);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);

	}
}
