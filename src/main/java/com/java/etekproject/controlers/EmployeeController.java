package com.java.etekproject.controlers;

import com.java.etekproject.dto.DepartmentDto;
import com.java.etekproject.dto.EmployeeDto;
import com.java.etekproject.model.Department;
import com.java.etekproject.model.Employee;
import com.java.etekproject.services.DepartmentService;
import com.java.etekproject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService service;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll()
    {
        return ResponseEntity.ok().body(service.getAllEmployees());
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody EmployeeDto employee)
    {
        employee.setEmployeeId(0);
        return service.add(employee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable long id)
    {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, Employee employee)
    {
        employee.setEmployeeId(id);
        return ResponseEntity.ok().body(service.edit(employee));
    }

    @DeleteMapping("/remove/{id}")
    public void deleteEmployee(@PathVariable long id)
    {
        if(service.findById(id) != null)
         service.remove(id);
    }

    @GetMapping("/departmentId/{departmentId}")
    public List<Employee> getByDepartmentId(@PathVariable Long departmentId)
    {
      return service.findByDepartmentId(departmentId);
    }

    @GetMapping("/firstName/{firstName}")
    public List<Employee> getEmployeesByFirstName(@PathVariable String firstName)
    {
        return service.findByFirstName(firstName);
    }

}
