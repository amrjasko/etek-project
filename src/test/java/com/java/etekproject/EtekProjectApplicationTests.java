package com.java.etekproject;

import com.java.etekproject.model.Employee;
import com.java.etekproject.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class EtekProjectApplicationTests {

	@Autowired
	EmployeeRepository repository;


	@Test
	void contextLoads() {
		Optional<Employee> byId = repository.findById(1l);
		if(byId.isPresent())
		{
			System.out.println("yes");
		}else{
			System.out.println("ni");
		}
	}

}
