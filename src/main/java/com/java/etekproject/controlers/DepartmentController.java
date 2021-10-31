package com.java.etekproject.controlers;

import com.java.etekproject.dto.DepartmentDto;
import com.java.etekproject.exceptions.APIException;
import com.java.etekproject.model.Department;
import com.java.etekproject.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController()
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping
    public ResponseEntity<List<Department>> getAll() {
        return ResponseEntity.ok().body(service.getAllDepartments());
    }

    @PostMapping("/add")
    public Department addDepartment(@RequestBody Department department) {
        return service.add(department);
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable long id) {
        if (service.findById(id).isPresent()) {
            return  service.findById(id).get();
        } else {
            throw  new APIException("Department not found by id "+id);
        }

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable long id, Department department) {
        department.setDepartmentId(id);
        return ResponseEntity.ok().body(service.edit(department));
    }

    @DeleteMapping("/remove/{id}")
    public void deleteDepartment(@PathVariable long id) {
         service.remove(id);
    }
}
