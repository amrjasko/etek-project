package com.java.etekproject.services;

import com.java.etekproject.exceptions.APIException;
import com.java.etekproject.model.Department;
import com.java.etekproject.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    public List<Department> getAllDepartments() {
        return repository.findAll();

    }

    public Department add(Department department)
    {
        department.setDepartmentId(0);
        return repository.save(department);
    }

    public Optional<Department> findById(long id) {
        return repository.findById(id);
    }

    public Department edit(Department department)
    {
        Optional<Department> departmentOptional = repository.findById(department.getDepartmentId());
        if(departmentOptional.isPresent())
        {
            Department tempdepartment = departmentOptional.get();
            tempdepartment.setDepartmentId(department.getDepartmentId());
            tempdepartment.setDepartmentName(department.getDepartmentName());
            tempdepartment.setManagerId(department.getManagerId());
            return repository.save(tempdepartment);
        }
        else{
            throw new APIException("Department not found with id "+department.getDepartmentId());
        }
    }

    public void remove(long id) {
        Optional<Department> department = repository.findById(id);
        if (department.isPresent()) {
            repository.deleteById(id);
        }else{
            throw new APIException("Department not found with id "+id);
        }
    }
}
