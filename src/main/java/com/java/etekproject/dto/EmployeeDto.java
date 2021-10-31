package com.java.etekproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private long employeeId;
    private String firstName;
    private String lastName;
    private Long managerId;
    private double salary;
    private Long departmentId;
    private Date hireDate;
    private String phoneNumber;
}
