package com.java.etekproject;

import com.java.etekproject.model.Employee;
import com.java.etekproject.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class EtekProjectApplication {



	public static void main(String[] args) {
		SpringApplication.run(EtekProjectApplication.class, args);
		System.out.println("Hi jasko");

	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}


}
