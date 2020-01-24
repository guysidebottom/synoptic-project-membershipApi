package com.springjpa.springpostgresjpa.controller;

import com.springjpa.springpostgresjpa.exception.EntityCreationException;
import com.springjpa.springpostgresjpa.exception.RecordNotFoundException;
import com.springjpa.springpostgresjpa.model.EmployeeEntity;
import com.springjpa.springpostgresjpa.model.EmployeeService;
import com.springjpa.springpostgresjpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class WebController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService service;

    // Welcome page
    @RequestMapping(value = "/v1/welcome/{cardId}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public String welcomePage(@PathVariable String cardId) throws RecordNotFoundException {
        EmployeeEntity employee = service.getEmployeeByCardId(cardId);
        if (employee.getCardId().isEmpty()) {
            throw new RecordNotFoundException("Card is not registered. Please register to use this service");
        }
        return String.format("Welcome to First Catering %s", employee.getName());
    }

    // Authentication
    @RequestMapping(value = "/v1/employee/auth", method = RequestMethod.GET)
    public String employeeAuthentication(@RequestParam int pinNumber, String cardId) throws RecordNotFoundException {
        EmployeeEntity employee = service.getEmployeeByCardId(cardId);
        if (employee.getPinNumber() == pinNumber) {
            employee.setLoggedIn(true);
            employeeRepository.save(employee);
            return String.format("Success! You are logged into First Catering %s", employee.getName());
        }
        return "Sorry your PIN number is invalid. Please try again.";
    }

    // GET employee by id
    @RequestMapping(value = "/v1/employee/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeEntity> findEmployeeById(@PathVariable int id) throws RecordNotFoundException {
        EmployeeEntity employee = service.getEmployeeById(id);
        return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    // GET employee card by id
    @RequestMapping(value = "/v1/employee/cardId", method = RequestMethod.GET)
    public ResponseEntity<EmployeeEntity> findCardById(@RequestParam("card_id") String cardId) throws RecordNotFoundException {
        EmployeeEntity employee = service.getEmployeeByCardId(cardId);
        return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    // create a new employee
    @RequestMapping(value = "/v1/employee/register", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public EmployeeEntity newEmployee(@RequestBody EmployeeEntity newEmployee) throws EntityCreationException {
        if (service.isRegistered(newEmployee.getCardId())) {
            throw new EntityCreationException("User already registered.");
        }
        return employeeRepository.save(newEmployee);
    }

    // replace an existing employee
    @RequestMapping(value = "/v1/employee/update/{id}", method = RequestMethod.PUT)
    public EmployeeEntity replaceEmployee(@RequestBody EmployeeEntity newEmployee, @PathVariable int id) {
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

    @RequestMapping(value = "/v1/employee/topup/{cardId}", method = RequestMethod.PUT)
    public EmployeeEntity topUpBalance(@RequestBody double amount, @PathVariable String cardId) throws RecordNotFoundException {
        EmployeeEntity employee = service.topUpBalance(cardId, amount);
        return employeeRepository.save(employee);
    }

}
