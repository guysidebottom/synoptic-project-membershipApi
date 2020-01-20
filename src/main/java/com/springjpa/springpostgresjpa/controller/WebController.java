package com.springjpa.springpostgresjpa.controller;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import com.springjpa.springpostgresjpa.model.Employee;
import com.springjpa.springpostgresjpa.model.EmployeeCard;
import com.springjpa.springpostgresjpa.repository.EmployeeCardRepository;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeCardRepository employeeCardRepository;

    // Employee CRUD methods
    // GET employee by id
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid ID")
    public String findEmployeeById(@PathVariable Long id) {
        String result = "";
        result += employeeRepository.findById(id).toString();
        return result;
    }

    // GET employee by name
    @RequestMapping(value = "/employee/name", method = RequestMethod.GET)
    public String findEmployeeByName(@RequestParam ("name") String name) {
        String result = "";
        result += employeeRepository.findByName(name);

        return result;
    }

    // create a new employee
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public Employee newEmployee(@RequestBody Employee newEmployee) throws EntityCreationException {
        if(newEmployee.getCardId() == null) {
            throw new EntityCreationException();
        }
        return employeeRepository.save(newEmployee);
    }

    // replace an existing employee
    @RequestMapping(value = "/employee/update/{id}", method = RequestMethod.PUT)
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable long id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setCardId(newEmployee.getCardId());
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
    // Create new employeeCard
    @RequestMapping(value = "/employeecard/{cardnumber}", method = RequestMethod.POST)
    public EmployeeCard newEmployeeCard(@RequestBody EmployeeCard employeeCard, @PathVariable int cardNumber) {
        return employeeCardRepository.save(employeeCard);
    }

    // GET employee card by id
    @RequestMapping(value = "/employeecard/{id}", method = RequestMethod.GET)
    public String findCardById(@PathVariable Long id) {
        String result = "";
        result += employeeCardRepository.findById(id).toString();
        return result;
    }
}
