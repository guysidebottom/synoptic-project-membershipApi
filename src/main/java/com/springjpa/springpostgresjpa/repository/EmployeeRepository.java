package com.springjpa.springpostgresjpa.repository;

import com.springjpa.springpostgresjpa.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByEmailAddress(String email);
    String findByName(String name);
}
