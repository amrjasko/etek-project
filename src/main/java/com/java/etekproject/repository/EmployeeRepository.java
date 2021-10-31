package com.java.etekproject.repository;

import com.java.etekproject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = "select * from mytest.Employees e where e.department_id = ?1",nativeQuery = true)
    Optional<List<Employee>> findByDepartmentId(long id);

    Optional<List<Employee>> getByFirstName(String firstName);
}
