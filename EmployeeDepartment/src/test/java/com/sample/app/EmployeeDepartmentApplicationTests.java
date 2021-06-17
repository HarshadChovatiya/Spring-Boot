package com.sample.app;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;

import com.sample.app.controller.DepartmentController;
import com.sample.app.controller.EmployeeController;

@SpringBootTest
//@RunWith(SpringRunner.class)
public class EmployeeDepartmentApplicationTests {
	
	@Autowired
	private EmployeeController employeeController;
	
	@Autowired
	private DepartmentController departmentController;
	
	@Test
	public void contextLoads() throws Exception {
//		assertThat(employeeController).isNotNull();
		assertThat(departmentController).isNotNull();
	}

	@Test
	public void testEmployeeControllerNull() throws Exception {
		assertThat(employeeController).isNotNull();
	}
}
