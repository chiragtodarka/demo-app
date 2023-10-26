package com.example.demoservice;

import com.example.demoservice.dto.Employee;
import com.example.demoservice.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoServiceApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void testCreateProduct() throws Exception {
		Employee employee = Employee.builder()
				.name("some name")
				.manager(-1)
				.build();

		mockMvc.perform(post("/employee")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(employee))
				)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.employeeId").exists())
				.andExpect(jsonPath("$.name").value("some name"))
				.andExpect(jsonPath("$.manager").value(-1))
		;
	}

}
