package com.java.etekproject.services;

import com.java.etekproject.dto.EmployeeDto;
import com.java.etekproject.exceptions.APIException;
import com.java.etekproject.model.Department;
import com.java.etekproject.model.Employee;
import com.java.etekproject.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Employee> getAllEmployees() {
//        repository.findAll().stream().map(e->convertEntityToDto(e)).collect(Collectors.toList());
        return repository.findAll();
    }
    public Employee add(EmployeeDto employeedto)
    {
        Employee employee = new Employee();
        employee.setEmployeeId(employeedto.getEmployeeId());
        Department d = new Department();
        d.setDepartmentId(employeedto.getDepartmentId());
        employee.setDepartmentId(d);
        employee.setFirstName(employeedto.getFirstName());
        employee.setLastName(employeedto.getLastName());
        employee.setHireDate(employeedto.getHireDate());
        employee.setPhoneNumber(employeedto.getPhoneNumber());
        employee.setManagerId(employeedto.getManagerId());
        return repository.save(employee);
    }

    public Employee findById(long id) {

        Optional<Employee> employeeOptional = repository.findById(id);
        if(employeeOptional.isPresent()) {
            return employeeOptional.get();
        }
        else{
            throw new APIException("Employee not found with id "+id);
        }
    }

    public Employee edit(Employee employee)
    {
        Optional<Employee> employeeOptional = repository.findById(employee.getEmployeeId());
         if(employeeOptional.isPresent())
         {
             return repository.save(employeeOptional.get());
         }else{
             throw new APIException("Employee not found with id "+employee.getEmployeeId());
         }

    }

    public void remove(long id) {
        Optional<Employee> emp = repository.findById(id);
        if (emp.isPresent()) {
            repository.deleteById(id);
        }else{
            throw new APIException("Employee not found with id "+id);
        }
    }

    public List<Employee> findByDepartmentId(Long departmentId) {

        Optional<List<Employee>> byDepartmentId = repository.findByDepartmentId(departmentId);
        if(byDepartmentId.isPresent()) {
            return byDepartmentId.get();
        }
        else{
            throw new APIException("Employee not found with department id "+departmentId);
        }
    }

    public List<Employee> findByFirstName(String firtName) {

        Optional<List<Employee>> byDepartmentId = repository.getByFirstName(firtName);
        if(byDepartmentId.isPresent()) {
            return byDepartmentId.get();
        }
        else{
            throw new APIException("Employee not found with first name "+firtName);
        }
    }

    private EmployeeDto convertEntityToDto(Employee employee) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        EmployeeDto employeeDTO = new EmployeeDto();
        employeeDTO = modelMapper.map(employee, EmployeeDto.class);
        return employeeDTO;
    }

    private Employee convertDtoToEntity(EmployeeDto employeeDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Employee employee = new Employee();
        employee = modelMapper.map(employeeDTO, Employee.class);
        return employee;
    }
}
