package com.springjpa.springpostgresjpa.controller;

import com.springjpa.springpostgresjpa.model.Employee;
import com.springjpa.springpostgresjpa.repository.ConsumableRepository;
import com.springjpa.springpostgresjpa.repository.EmployeeCardRepository;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeCardRepository employeeCardRepository;
    @Autowired
    ConsumableRepository consumableRepository;

    // Employee CRUD methods
    // GET employee by id
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable Long id) {
        String result = "";
        result += employeeRepository.findById(id).toString();
        return result;
    }

    // GET employee by name
    @RequestMapping(value = "/employee/name", method = RequestMethod.GET)
    public String findByName(@RequestParam ("name") String name) {
        String result = "";
        result += employeeRepository.findByName(name);

        return result;
    }

    // create a new employee
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    // replace an existing employee
    @RequestMapping(value = "/employee/update/{id}", method = RequestMethod.PUT)
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setEmailAddress(newEmployee.getEmailAddress());
                    employee.setPhoneNumber(newEmployee.getPhoneNumber());
                    employee.setPinNumber(newEmployee.getPinNumber());
                    return employeeRepository.save(newEmployee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }

    // EmployeeCard CRUD methods


}
